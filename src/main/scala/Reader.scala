import java.io.{FileInputStream, File}
import Conversions._

/**
 * User: mthorpe
 * Date: 28/01/2013
 * Time: 14:58
 */

class HDRPixel(r:Float,g:Float,b:Float)
class Image(val image:Array[HDRPixel])


class Reader(file:File) {

  val in = new FileInputStream(file)
  val fileType:String = getNextElem()

  val xDim:Int = this.getNextElem().toInt
  val yDim:Int = this.getNextElem().toInt

  assert(xDim > 0); assert(yDim > 0); assert(fileType == "PF")

  val endian:Float = this.getNextElem().toFloat
  val image:Image = this.getImage()

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

  def getImage():Image = {
    val img = new Image(new Array[HDRPixel](xDim*yDim))

    for (x <- 0 to ((xDim*yDim) -1 ))
      img(x) = new HDRPixel(in.read().toFloat,in.read().toFloat,in.read().toFloat)

    img
  }
}
