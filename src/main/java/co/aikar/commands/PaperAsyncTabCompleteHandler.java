package co.aikar.commands;

import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Arrays;
import java.util.List;

class PaperAsyncTabCompleteHandler implements Listener {
    private final PaperCommandManager manager;

    PaperAsyncTabCompleteHandler(PaperCommandManager manager) {
        this.manager = manager;
    }

    @EventHandler(ignoreCancelled = true)
    public void onAsyncTabComplete(AsyncTabCompleteEvent event) {
        String buffer = event.getBuffer();
        if ((!event.isCommand() && !buffer.startsWith("/")) || buffer.indexOf(' ') == -1) {
            return;
        }
        try {
            //noinspection ConstantConditions,ConstantIfStatement
            if (false) throw new CommandCompletions.SyncCompletionRequired(); // fake compiler due to SneakyThrows
            List<String> completions = getCompletions(buffer, event.getCompletions(), event.getSender(), true);
            if (completions != null) {
                event.setCompletions(completions);
                event.setHandled(true);
            }
        } catch (Exception e) {
            if (!(e instanceof CommandCompletions.SyncCompletionRequired)) {
                throw e;
            }
        }
    }

    private List<String> getCompletions(String buffer, List<String> existingCompletions, CommandSender sender, boolean async) {
        String[] args = ACFPatterns.SPACE.split(buffer, -1);

        String commandLabel = stripLeadingSlash(args[0]);
        args = args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : new String[]{""};

        RootCommand rootCommand = this.manager.getRootCommand(commandLabel);
        if (rootCommand == null) {
            return null;
        }

        BukkitCommandIssuer issuer = this.manager.getCommandIssuer(sender);
        List<String> completions = rootCommand.getTabCompletions(issuer, commandLabel, args, false, async);

        return ACFUtil.preformOnImmutable(existingCompletions, (list) -> list.addAll(completions));
    }

    private static String stripLeadingSlash(String arg) {
        return arg.startsWith("/") ? arg.substring(1) : arg;
    }
}
