/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 drakeet (drakeet.me@gmail.com)
 * http://drakeet.me
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.lib.crash;

import java.util.ArrayList;

/**
 * @author drakeet
 */
public interface UncaughtExceptionInterceptor {

    /**
     * Called before this uncaught exception be handled by {@link
     * CrashWoodpecker}.
     *
     * @param message 设备的信息
     * @param throwable the exception
     * @return true if intercepted, which means this event won't be handled
     * by {@link CrashWoodpecker}.
     */
    boolean onBeforeHandlingException(Throwable throwable, ArrayList<String> message);

    /**
     * Called after this uncaught exception be handled by
     * {@link CrashWoodpecker} (but before {@link CrashWoodpecker}'s
     * parent).
     *
     * @param message 设备的信息
     * @param throwable the exception
     * @return true if intercepted, which means this event won't be handled
     * by {@link CrashWoodpecker}'s parent.
     */
    boolean onAfterHandlingException(Throwable throwable, ArrayList<String> message);
}
