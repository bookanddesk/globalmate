package com.globalmate.service.match.auto;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author XingJiajun
 * @Date 2018/7/12 18:47
 * @Description
 */
public class CommandInvoker {

    private List<Command> commands = Lists.newArrayList();

    public void addCommand(Command command) {
        if (command instanceof MatchCommand) {

        }else if (command instanceof MessageCommand) {

        }
        commands.add(command);
    }

    public boolean cancleCommand(Command command) {
        return commands.remove(command);
    }

    public void invokCommand() {
        for (Command command : commands) {
            command.execute();
        }
    }

}
