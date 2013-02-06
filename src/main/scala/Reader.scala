import java.io.{DataInputStream, FileInputStream, File}
import Conversions._
import runtime.RichFloat

/**
 * User: mthorpe
 * Date: 28/01/2013
 * Time: 14:58
 */

final class Reader(file:File) {

  val in = new FileInputStream(file)
  val dat = new DataInputStream(in)
  val fileType:String = getNextElem()

  val xDim:Int = this.getNextElem().toInt
  val yDim:Int = this.getNextElem().toInt

  assert(xDim > 0); assert(yDim > 0); assert(fileType == "PF")

  val endian:Float = this.getNextElem().toFloat
  val image:Image = this.getImage()

  dat.close()

  def getNextElem():String = {
    val builder = new StringBuilder
    var currentByte:Int = Int.MinValue

    // lol
    while (true) {
      currentByte = dat.read()
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
    val img = new Image(Array.ofDim[HDRPixel](xDim,yDim))

    for (x <- 0 to xDim - 1)
      for (y <- 0 to yDim - 1)
        img(x)(y) = new HDRPixel(dat.readFloat().toDouble,dat.readFloat().toDouble,dat.readFloat().toDouble)

    img
  }
}
