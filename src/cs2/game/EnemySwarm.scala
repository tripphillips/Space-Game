package cs2.game

import scalafx.scene.image.Image
import scalafx.scene.canvas.Canvas
import scalafx.scene.input.KeyEvent
import scalafx.scene.input.KeyCode
import cs2.util.Vec2
import scalafx.scene.canvas.GraphicsContext

/** contains the control and logic to present a coordinated set of Enemy objects.
 *  For now, this class generates a "grid" of enemy objects centered near the 
 *  top of the screen.
 *  
 *  @param nRows - number of rows of enemy objects
 *  @param nCols - number of columns of enemy objects
 */
  class EnemySwarm(private val nRows:Int, private val nCols:Int) extends ShootsBullets {
	
	/** method to display all Enemy objects contained within this EnemySwarm
	 * 
	 *  @param g - the GraphicsContext to draw into
	 *  @return none/Unit
	 */
  
     val grid = Array.ofDim[Enemy](nRows,nCols)
   
     for (x <- 0 until nRows) {
         for (y <- 0 until nCols) {
       
          val pic = new Image("file:CS2Alien.png")
          val bPic = new Image("file:CS2GreenLaser.png")
          val initPos = new Vec2(x*90,y*50)
        
          grid(x)(y) = new Enemy(pic, initPos, bPic)
     
       }
     }
  
	  def display(gc:GraphicsContext) { 
  
	      for (x <- 0 until nRows) {
	        for (y <- 0 until nCols) {
	      
	          grid(x)(y).display(gc,40, 40)
	      
	         }
	      }
	  }
  /** overridden method of ShootsBullets. Creates a single, new bullet instance 
   *  originating from a random enemy in the swarm. (Not a bullet from every 
   *  object, just a single from a random enemy)
   *  
   *  @return Bullet - the newly created Bullet object fired from the swarm
   */
   def shoot():Bullet = { 
     
	     var rRow = ((nRows+1)*(math.random())).toInt
	     var rCol = ((nCols+1)*(math.random())).toInt
	      
	     var enemy = grid(rRow)(rCol)
	     enemy.shoot()
	     
   }
}