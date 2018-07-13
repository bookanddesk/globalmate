package com.globalmate.service.match.auto;

/**
 * @author XingJiajun
 * @Date 2018/7/12 18:39
 * @Description
 */
public class MatchCommand extends Command{

    public MatchCommand(IMatchExecuteSevice IMatchExecuteSevice) {
        super(IMatchExecuteSevice);
    }

    @Override
    public void execute() {
        IMatchExecuteSevice.matchAction();
    }
}
