package com.tsatsatzu.subwar.game.api;import com.tsatsatzu.subwar.game.data.SWContextBean;import com.tsatsatzu.subwar.game.data.SWOperationBean;import com.tsatsatzu.subwar.game.data.SWUserBean;import com.tsatsatzu.subwar.game.logic.CredentialsLogic;import com.tsatsatzu.subwar.game.logic.IOLogic;import com.tsatsatzu.subwar.game.logic.UserLogic;public class SubWarGameAPI{    public static SWContextBean invoke(SWOperationBean op)    {        SWContextBean context = new SWContextBean();        context.setOperation(op);        if (!CredentialsLogic.validateAPIKey(op.getCredentials()))        {            context.setLastOperationError("Invalid Credentials");            return context;        }        SWUserBean user = UserLogic.validate(op.getUserID());        context.setUser(user);        switch (op.getOperation())        {            case SWOperationBean.ENTER_GAME:                enterGame(context);                break;            case SWOperationBean.MOVE:                move(context);                break;            case SWOperationBean.QUERY_USER:                queryUser(context);                break;            case SWOperationBean.SET_USER_DETAILS:                setUserDetails(context);                break;            case SWOperationBean.TEST:                test(context);                break;            default:                context.setLastOperationError("Unknown operation: "+op.getOperation());                return context;        }        user = context.getUser();        user.setLastInteraction(System.currentTimeMillis());        user.setNumberOfInteractions(user.getNumberOfInteractions() + 1);        IOLogic.saveUser(user);        return context;    }    private static void setUserDetails(SWContextBean context)    {        if (context.getOperation().getString1() != null)            UserLogic.setUserName(context.getUser(), context.getOperation().getString1());        if (context.getOperation().getString2() != null)            UserLogic.setShipName(context.getUser(), context.getOperation().getString2());    }    private static void queryUser(SWContextBean context)    {        // NO-OP. User already retrieved above.    }    private static void move(SWContextBean context)    {        throw new IllegalStateException("not implemented yet");    }    private static void test(SWContextBean context)    {        throw new IllegalStateException("not implemented yet");    }    private static void enterGame(SWContextBean context)    {        throw new IllegalStateException("not implemented yet");    }}