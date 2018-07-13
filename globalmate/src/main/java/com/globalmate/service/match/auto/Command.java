package com.globalmate.service.match.auto;

/**
 * @author XingJiajun
 * @Date 2018/7/12 18:28
 * @Description
 */
public abstract class Command {

    protected IMatchExecuteSevice IMatchExecuteSevice;

    public Command(IMatchExecuteSevice IMatchExecuteSevice) {
        this.IMatchExecuteSevice = IMatchExecuteSevice;
    }

    public abstract void execute();
}
