package com.tsatsatzu.subwar.audio.logic;

import com.tsatsatzu.subwar.audio.data.SWInvocationBean;
import com.tsatsatzu.subwar.game.data.SWGameDetailsBean;
import com.tsatsatzu.subwar.game.data.SWOperationBean;
import com.tsatsatzu.subwar.game.data.SWPositionBean;

public class MoveLogic
{
    public static void north(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 0, -1, 0, SWOperationBean.NORTH);
    }

    public static void south(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 0, 1, 0, SWOperationBean.SOUTH);
    }

    public static void east(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 1, 0, 0, SWOperationBean.EAST);
    }

    public static void west(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, -1, 0, 0, SWOperationBean.WEST);
    }

    public static void northwest(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, -1, -1, 0, SWOperationBean.NORTHWEST);
    }

    public static void northeast(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 1, -1, 0, SWOperationBean.NORTHEAST);
    }

    public static void southwest(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, -1, 1, 0, SWOperationBean.SOUTHWEST);
    }

    public static void southeast(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 1, 1, 0, SWOperationBean.SOUTHEAST);
    }

    public static void dive(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 0, 0, 1, SWOperationBean.LOWER);
    }

    public static void rise(SWInvocationBean ssn) throws SWAudioException
    {
        doMove(ssn, 0, 0, -1, SWOperationBean.RAISE);
    }

    private static void doMove(SWInvocationBean ssn, int dLon, int dLat, int dDep, int dir) throws SWAudioException
    {
        SWGameDetailsBean game = ssn.getGame();
        SWPositionBean pos = game.getUserPosition();
        int newLon = pos.getLongitude() + dLon;
        int newLat = pos.getLattitude() + dLat;
        int newDep = pos.getDepth() + dDep;
        if (newDep > game.getMaxDepth())
        {
            ssn.addText("We can't go that deep {captain}!");
            ssn.addText("{Ship} isn't rated for that depth.");
            ssn.addPause();
            ssn.addText("Just say rise it you meant for us to go up.");
            return;
        }
        if (newDep < 0)
        {
            ssn.addText("Um, {captain}, we're already on the surface.");
            ssn.addText("{Ship} isn't rated for that depth.");
            ssn.addPause();
            ssn.addText("Tell us to dive if you want us to go down instead.");
            return;
        }
        if (newLon < game.getWest())
        {
            ssn.addText("I can't do that, {captain}.");
            ssn.addText("That would run {ship} on to the Western shore.");
            ssn.addPause();
            ssn.addText("What direction would you like us to go?");
            return;
        }
        if (newLon > game.getEast())
        {
            ssn.addText("I can't comply, {captain}.");
            ssn.addText("{Ship} would run up on the Eastern shore if we tried.");
            ssn.addPause();
            ssn.addText("What direction would you like us to go?");
            return;
        }
        if (newLat < game.getNorth())
        {
            ssn.addText("That would take us out of the Acton straights, {captain}.");
            ssn.addText("There aren't any targets there.");
            ssn.addPause();
            ssn.addText("You might consider giving the order to go South instead.");
            return;
        }
        if (newLat > game.getSouth())
        {
            ssn.addText("That would take us out of the Acton straights, {captain}.");
            ssn.addText("I don't think we're ready to give up yet.");
            ssn.addPause();
            ssn.addText("You might consider giving the order to go North instead.");
            return;
        }
        InvocationLogic.game(ssn, SWOperationBean.MOVE, dir, null);
        ssn.addSound(AudioConstLogic.SOUND_MOTOR_RUNNING);
        //InvocationLogic.
        PlayLogic.describeGame(ssn);
        ssn.addPause();
        ssn.addText("What are your orders now?");
    }

}
