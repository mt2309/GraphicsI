import java.io.{FilenameFilter, File}
import Conversions._

/**
 * User: mthorpe
 * Date: 28/01/2013
 * Time: 14:49
 */
object Main extends App {

  val help = "Argument 1: mode, Argument 1: directory containing .pfm files of the same resolution"

  assert(args.length > 1,help)

  args(0) match {
    case "assemble" | "HDR" | "hdr" | "1" | "one" => hdr()
    case "lighting" | "image-based-lighting" | "2" | "two" => lighting()
    case _ => {println(help); sys.exit(1)}
  }

  private def hdr() {
    val images = getFiles(new File(args(1))) map (x => (new Reader(x)).image)

    assert(images.length > 0, help)

    // check the files are the same resolution
    images foreach (x => assert(x.length == images(0).length &&
      (x(0).length == images(0)(0).length), "Image is not the correct resolution, " ++ help))


    new Writer((new ExposureHandler(images)).reduced, "f.pfm")
  }

  private def lighting() {
    val image = new Lighting(new Reader(new File(args(1))).image)
  }


  def getFiles(directory:File):Array[File] = {
    directory listFiles(new FilenameFilter {
      def accept(dir: File, name: String) = (name.endsWith(".pfm") || name.endsWith(".PFM"))
    })
  }
}
