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

    fun addNumberOfMines() {
        for (raw in 0..this.raw-1) {
            for (column in 0..this.column-1) {
                if (this.field[raw][column] == "X") continue
                else {
                    var count = 0
                    if (raw in 1 until this.raw-1) {
                        when {
                            column in 1 until this.raw-1 -> {
                                if (this.field[raw-1][column-1] == "X") count++
                                if (this.field[raw-1][column] == "X") count++
                                if (this.field[raw-1][column+1] == "X") count++
                                if (this.field[raw][column-1] == "X") count++
                                if (this.field[raw][column+1] == "X") count++
                                if (this.field[raw+1][column-1] == "X") count++
                                if (this.field[raw+1][column] == "X") count++
                                if (this.field[raw+1][column+1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                            column == 0 -> {
                                if (this.field[raw-1][column] == "X") count++
                                if (this.field[raw-1][column+1] == "X") count++
                                if (this.field[raw][column+1] == "X") count++
                                if (this.field[raw+1][column] == "X") count++
                                if (this.field[raw+1][column+1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                            column == this.column-1 -> {
                                if (this.field[raw-1][column] == "X") count++
                                if (this.field[raw-1][column-1] == "X") count++
                                if (this.field[raw][column-1] == "X") count++
                                if (this.field[raw+1][column] == "X") count++
                                if (this.field[raw+1][column-1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }

                        }

                    }
                    else if (raw == 0) {
                        when {
                            column in 1 until this.column-1 -> {
                                if (this.field[raw][column-1] == "X") count++
                                if (this.field[raw][column+1] == "X") count++
                                if (this.field[raw+1][column-1] == "X") count++
                                if (this.field[raw+1][column] == "X") count++
                                if (this.field[raw+1][column+1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                            column == 0 -> {
                                if (this.field[raw][column+1] == "X") count++
                                if (this.field[raw+1][column] == "X") count++
                                if (this.field[raw+1][column+1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                            column == this.column-1 -> {
                                if (this.field[raw][column-1] == "X") count++
                                if (this.field[raw+1][column] == "X") count++
                                if (this.field[raw+1][column-1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                        }

                    }
                    else if (raw == this.raw-1) {
                        when {
                            column in 1 until this.column-1 -> {
                                if (this.field[raw][column-1] == "X") count++
                                if (this.field[raw][column+1] == "X") count++
                                if (this.field[raw-1][column-1] == "X") count++
                                if (this.field[raw-1][column] == "X") count++
                                if (this.field[raw-1][column+1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                            column == 0 -> {
                                if (this.field[raw][column+1] == "X") count++
                                if (this.field[raw-1][column] == "X") count++
                                if (this.field[raw-1][column+1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                            column == this.column-1 -> {
                                if (this.field[raw][column-1] == "X") count++
                                if (this.field[raw-1][column] == "X") count++
                                if (this.field[raw-1][column-1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                        }
                    }
                    //else if (column == 0) {
                        //when {
                        //    raw in 1 until this.raw-1 -> {
                        //        if (this.field[raw-1][column] == "X") count++
                        //        if (this.field[raw-1][column+1] == "X") count++
                         //       if (this.field[raw][column+1] == "X") count++
                         //       if (this.field[raw+1][column] == "X") count++
                          //      if (this.field[raw+1][column+1] == "X") count++
                         //       if (count != 0) this.field[raw][column] = count.toString()
                         //   }
                       // }
                    }

                }
            }
        }
    }
    


fun main() {
    
    println("How many mines do you want on the field? ")
    val mines = readLine()!!.toInt()
    val minesweeper = Minefield(9, 9, mines)
    minesweeper.addMines()
    minesweeper.addNumberOfMines()
    for (i in minesweeper.field) println(i.joinToString(""))
}
