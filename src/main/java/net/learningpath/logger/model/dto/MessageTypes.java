package net.learningpath.logger.model.dto;

import java.util.logging.Level;

public enum MessageTypes {
    INFO(1, Level.INFO),
    ERROR(2, Level.SEVERE),
    WARNING(3, Level.WARNING);

    private int id;
    private Level level;

    MessageTypes(int id, Level level) {
        this.id = id;
        this.level = level;
    }

    public int getId() {
        return id;
    }

    public Level getLevel() {
        return level;
    }
}
