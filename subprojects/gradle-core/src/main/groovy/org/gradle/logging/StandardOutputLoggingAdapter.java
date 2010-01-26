/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gradle.logging;

import org.gradle.api.logging.LogLevel;
import org.gradle.api.logging.Logger;
import org.gradle.util.LineBufferingOutputStream;

/**
 * @author Hans Dockter
 */
public class StandardOutputLoggingAdapter extends LineBufferingOutputStream {
    /**
     * The category to write to.
     */
    private Logger logger;

    /**
     * The priority to use when writing to the Category.
     */
    private LogLevel level;

    /**
     * Creates the OutputStream to flush to the given Category.
     *
     * @param log the Logger to write to
     * @param level the Level to use when writing to the Logger
     * @throws IllegalArgumentException if cat == null or priority == null
     */
    public StandardOutputLoggingAdapter(Logger log, LogLevel level)
            throws IllegalArgumentException {
        this(log, level, 2048);
    }

    /**
     * Creates the OutputStream to flush to the given Category.
     *
     * @param log the Logger to write to
     * @param level the Level to use when writing to the Logger
     * @param bufferLength The initial buffer length to use
     * @throws IllegalArgumentException if cat == null or priority == null
     */
    public StandardOutputLoggingAdapter(Logger log, LogLevel level, int bufferLength)
            throws IllegalArgumentException {
        super(bufferLength);

        this.level = level;
        logger = log;
    }

    @Override
    protected void writeLine(String message) {
        logger.log(level, message);
    }

    public LogLevel getLevel() {
        return level;
    }

    public void setLevel(LogLevel level) {
        this.level = level;
    }
}

