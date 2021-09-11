/*
 * MIT License
 *
 * Copyright (c) 2024.  Grabtaxi Holdings Pte Ltd (GRAB), All rights reserved.
 *
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE
 */

package com.grab.sizer.utils

import java.io.File
import java.io.IOException
import javax.inject.Inject


interface FileQuery {
    fun query(dir: File, vararg extensions: String): Sequence<File>
}

class DefaultFileQuery @Inject constructor() : FileQuery {

    @Throws(IOException::class)
    override fun query(dir: File, vararg extensions: String): Sequence<File> {
        if (dir.isFile) throw IOException("${dir.path} is not a directory")
        return dir.walk()
            .filter { file ->
                file.isFile && extensions.any { ext ->
                    file.extension.equals(ext, true)
                }
            }
    }
}



