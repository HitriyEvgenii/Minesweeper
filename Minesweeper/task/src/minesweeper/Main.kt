package minesweeper
import java.util.Random

class Minefield(val raw: Int, val column: Int, val numberOfMines: Int = 0) {
    val field = MutableList<MutableList<String>>(this.raw) {
        MutableList<String>(this.column) {"."}
        }
        
    fun addMines() {
        var count = 0
        while (count != this.numberOfMines){
            count++
            //var raw = Random().nextInt(0, this.raw)
            var raw = (0..this.raw-1).random()
            var column = (0..this.column-1).random()
            if (field[raw][column] =="X") count--
            field[raw][column] = "X"
        }
        
    }
    
}

fun main() {
    
    println("How many mines do you want on the field? ")
    val mines = readLine()!!.toInt()
    val minesweeper = Minefield(9, 9, mines)
    minesweeper.addMines()
    for (i in minesweeper.field) println(i.joinToString(""))
}
