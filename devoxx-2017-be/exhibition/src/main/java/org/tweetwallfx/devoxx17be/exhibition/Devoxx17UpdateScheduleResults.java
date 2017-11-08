/*
 * The MIT License
 *
 * Copyright 2017 TweetWallFX
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.tweetwallfx.devoxx17be.exhibition;

import java.time.Duration;
import java.time.LocalDateTime;
import org.tweetwallfx.controls.WordleSkin;
import org.tweetwallfx.controls.stepengine.AbstractStep;
import org.tweetwallfx.controls.stepengine.StepEngine;

/**
 * Step to trigger the updating of the schedule
 *
 * @author Sven Reimers
 */
public class Devoxx17UpdateScheduleResults extends AbstractStep {

    private LocalDateTime nextUpDateTime = LocalDateTime.now().minusMinutes(5);;
    
    @Override
    public void doStep(StepEngine.MachineContext context) {
        WordleSkin wordleSkin = (WordleSkin) context.get("WordleSkin");
        final ScheduleDataProvider scheduleProvider = wordleSkin.getSkinnable().getDataProvider(ScheduleDataProvider.class);
        scheduleProvider.updateSchedule();
        nextUpDateTime = LocalDateTime.now().plusMinutes(15);                
        context.proceed();
    }

    @Override
    public Duration preferredStepDuration(StepEngine.MachineContext context) {
        return Duration.ZERO;
    }

    @Override
    public boolean shouldSkip(StepEngine.MachineContext context) {
        return LocalDateTime.now().isBefore(nextUpDateTime);
    }
    
}