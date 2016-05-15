/*
 * Copyright 2016 Jo Jaquinta, TsaTsaTzu
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tsatsatzu.subwar.game.logic.ai;

import com.tsatsatzu.subwar.game.data.SWGameBean;

// TODO: Auto-generated Javadoc
/**
 * The Interface IComputerPlayer.
 */
public interface IComputerPlayer
{
    
    /**
     * Inits the.
     *
     * @param game the game
     * @param id the id
     */
    public void init(SWGameBean game, String id);
    
    /**
     * Move.
     *
     * @param game the game
     * @param id the id
     * @param tick the tick
     */
    public void move(SWGameBean game, String id, long tick);
    
    /**
     * Term.
     *
     * @param game the game
     * @param id the id
     */
    public void term(SWGameBean game, String id);
}
