package com.tinchop.bowling.model.frame;

import com.tinchop.bowling.model.Printable;

public interface Frame extends Printable {

    Integer getScore();

    String getFirstChance();

    void setPreviousFrame(Frame frame);

    void setNextFrame(Frame frame);

    Integer parseChance(String chance);

    Integer sayStrikeBonusToPrevious();

}
