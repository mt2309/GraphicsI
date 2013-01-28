import java.io.{FilenameFilter, File}
import PFMReader.Reader

/**
 * User: mthorpe
 * Date: 28/01/2013
 * Time: 14:49
 */
object Main extends App {

  assert(args.length > 0)

  val files:Set[File] = getFiles(new File(args(0)))
  val images = files map(x => (new Reader(x)).image)

  def getFiles(directory:File):Set[File] = {
    directory.listFiles(new FilenameFilter {
      def accept(dir: File, name: String) = (name.endsWith(".pfm") || name.endsWith(".PFM"))
    }).toSet
  }

}
