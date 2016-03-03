/*
 * Copyright 2016 Actian Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.actian.spark_vector.colbuffer.integer

import com.actian.spark_vector.colbuffer._

import java.nio.ByteBuffer

private class ShortColumnBuffer(valueCount: Int, name: String, index: Int, nullable: Boolean) extends
              ColumnBuffer[Short](valueCount, ShortSize, ShortSize, name, index, nullable) {

  override protected def put(source: Short, buffer: ByteBuffer): Unit = {
    buffer.putShort(source);
  }
}

/** `ColumnBuffer` object for `smallint`, `integer2` types. */
object ShortColumnBuffer extends ColumnBufferInstance[Short] {

  private[colbuffer] override def getNewInstance(name: String, index: Int, precision: Int, scale: Int,
                                                 nullable: Boolean, maxRowCount: Int): ColumnBuffer[Short] = {
    new ShortColumnBuffer(maxRowCount, name, index, nullable)
  }

  private[colbuffer] override def supportsColumnType(tpe: String, precision: Int, scale:Int, nullable: Boolean): Boolean = {
    tpe.equalsIgnoreCase(ShortTypeId1) || tpe.equalsIgnoreCase(ShortTypeId2)
  }
}
