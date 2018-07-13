package com.globalmate.service.match.auto;

/**
 * @author XingJiajun
 * @Date 2018/7/12 18:43
 * @Description
 */
public class MessageCommand extends Command {

    public MessageCommand(IMatchExecuteSevice IMatchExecuteSevice) {
        super(IMatchExecuteSevice);
    }

    @Override
    public void execute() {
        IMatchExecuteSevice.matchAction();
    }
}
