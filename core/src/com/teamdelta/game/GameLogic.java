package com.teamdelta.game;

import com.badlogic.gdx.math.MathUtils;
import com.teamdelta.game.entities.EntityNames;
import com.teamdelta.game.entities.RPSLSEntity;

import java.util.LinkedHashMap;

/**
 * @author William Warnock
 */
public class GameLogic {
    private AbstractGameMode game;
    private LinkedHashMap<String, RPSLSEntity> entitiesMap = new LinkedHashMap<String, RPSLSEntity>();

    public GameLogic(AbstractGameMode game) {
        this.game = game;

        loadHashMap();
        setupFoes();
    }

    /**
     * Get matching entity by Enum
     * @param choice
     * @return
     */
    public RPSLSEntity getEntity(Enum choice) {
        return entitiesMap.get(choice.toString());
    }

    /**
     * Get randomly selected entity
     * @return
     */
    public RPSLSEntity getEntity() {
        int entityIndex = MathUtils.random(0, 4);

        return entitiesMap.get(EntityNames.values()[entityIndex].toString());
    }

    /**
     * Determines the winner of the game
     *
     * -1 => The match was a draw
     *  0 => CPU wins
     *  1 => Player wins
     *
     * @param playerEntity RPSLSEntity representing the user's selection
     * @param cpuEntity RPSLSEntity representing the cpu's selection
     * @return {int}
     */
    public int determineWinner(RPSLSEntity playerEntity, RPSLSEntity cpuEntity) {
        int winner = -1;

        if(playerEntity.getName().equals(cpuEntity.getName())) {
            winner = -1;
        } else if(playerEntity.isFoe(cpuEntity)) {
            winner = 0;
        } else if(cpuEntity.isFoe(playerEntity)) {
            winner = 1;
        }

        return winner;
    }

    /**
     * Put RPSLSEntities into HashMap for comparisons
     */
    private void loadHashMap() {
        entitiesMap.put(game.rockEntity.getName(), game.rockEntity);
        entitiesMap.put(game.paperEntity.getName(), game.paperEntity);
        entitiesMap.put(game.scissorsEntity.getName(), game.scissorsEntity);
        entitiesMap.put(game.lizardEntity.getName(), game.lizardEntity);
        entitiesMap.put(game.spockEntity.getName(), game.spockEntity);
    }

    /**
     * Setup foes for each RPSLSEntity
     */
    private void setupFoes() {
        //Foes of rock
        game.rockEntity.addFoe(game.paperEntity);        //Paper covers rock
        game.rockEntity.addFoe(game.spockEntity);        //Spock vaporizes rock

        //Foes of paper
        game.paperEntity.addFoe(game.scissorsEntity);    //Scissors cut paper
        game.paperEntity.addFoe(game.lizardEntity);      //Lizard eats paper

        //Foes of scissors
        game.scissorsEntity.addFoe(game.rockEntity);     //Rock crushes scissors
        game.scissorsEntity.addFoe(game.spockEntity);    //Spock smashes scissors

        //Foes of lizard
        game.lizardEntity.addFoe(game.rockEntity);       //Rock crushes lizard
        game.lizardEntity.addFoe(game.scissorsEntity);   //Scissors decapitates lizard

        //Foes of spock
        game.spockEntity.addFoe(game.paperEntity);       //Paper disproves spock
        game.spockEntity.addFoe(game.lizardEntity);      //Lizard poisons spock
    }
}
