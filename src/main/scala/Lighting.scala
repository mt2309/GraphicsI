/**
 * User: mthorpe
 * Date: 09/02/2013
 * Time: 22:39
 */
final class Lighting(image:Image) {

  // Check we have a square image
  assert(image.xDim == image.yDim)

  private val center = image.xDim/2
  private val radius = image.xDim/2



  private def inCircle(x:Int,y:Int):Boolean = math.pow(x - center,2) + math.pow(y - center,2) < math.pow(radius,2)



}
