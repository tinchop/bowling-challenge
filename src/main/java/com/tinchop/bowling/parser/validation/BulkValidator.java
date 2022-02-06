package com.tinchop.bowling.parser.validation;

import java.util.List;
import java.util.Map;

public interface BulkValidator {

    void validateBulk(Map<String, List<String>> bulk);

}
