import java.io.{FilenameFilter, File}
import Conversions._

/**
 * User: mthorpe
 * Date: 28/01/2013
 * Time: 14:49
 */
object Main extends App {

  val help = "Argument 1: directory containing .pfm files of the same resolution"

  assert(args.length > 0,help)

  val images = getFiles(new File(args(0))) map(x => (new Reader(x)).image)

  assert(images.length > 0, help)

  // check the files are the same resolution
  images foreach(x => assert(x.length == images(0).length &&
    (x(0).length == images(0)(0).length),"Image is not the correct resolution, " ++ help))


  val exposures = new Writer((new ExposureHandler(images)).reduced,"f.pfm")

  def getFiles(directory:File):Array[File] = {
    directory listFiles(new FilenameFilter {
      def accept(dir: File, name: String) = (name.endsWith(".pfm") || name.endsWith(".PFM"))
    })
  }
}
