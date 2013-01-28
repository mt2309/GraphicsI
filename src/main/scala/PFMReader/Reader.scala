package PFMReader

import java.io.{FileInputStream, File}

/**
 * User: mthorpe
 * Date: 28/01/2013
 * Time: 14:58
 */

class HDRPixel(r:Float,g:Float,b:Float)

class Reader(file:File) {

  val in = new FileInputStream(file)

  val fileType:String = getNextElem()

  assert(fileType == "PF")

  val xDim:Int = this.getNextElem().toInt
  val yDim:Int = this.getNextElem().toInt

  assert(xDim > 0); assert(yDim > 0)

  val endian:Float = this.getNextElem().toFloat

  val image:Array[Float] = this.getImage()

  def getNextElem():String = {
    val builder = new StringBuilder
    var currentByte:Int = Int.MinValue

    // lol
    while (true) {
      currentByte = in.read()
      if (!currentByte.toChar.isWhitespace) {
        builder.append(currentByte.toChar)
      }
      else {
        return builder.toString()
      }
    }
    builder.toString()
  }

  def getImage():Array[Float] = {
    val img = new Array[Float](xDim*yDim*3)

    for (x <- 0 to ((xDim*yDim*3) -1 )) {
      img(x) = in.read().toFloat
    }

    img
  }
}
