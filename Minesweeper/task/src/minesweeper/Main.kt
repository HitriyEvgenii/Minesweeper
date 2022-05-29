package minesweeper
import java.util.Random

class Minefield(val raw: Int, val column: Int, val numberOfMines: Int = 0) {
    val field = MutableList<MutableList<String>>(this.raw) {
        MutableList<String>(this.column) {"."}
        }
    val MinesCoordinate = emptyList<MutableList<Int>>().toMutableList()

    init {
        addMines()
        addNumberOfMines()
        val word = (1..this.column).toList()
        println(" |${word.joinToString("")}|")
           
        print("-|")
        for (i in 1..this.column) print("-")
        print("|")
        println()
        var count = 1
        for (i in this.field) {
            println("${count++}|${i.joinToString("")}|")
            //print(i.joinToString(""))
        }
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
            MinesCoordinate.add(mutableListOf(raw, column))
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
                    }

                }
            }
        }
}
    


fun main() {
    
    println("How many mines do you want on the field? ")
    val mines = readLine()!!.toInt()
    val minesweeper = Minefield(9, 9, mines)
    //println(minesweeper.MinesCoordinate)
    //minesweeper.addMines()
    //minesweeper.addNumberOfMines()
    //for (i in minesweeper.field) println(i.joinToString(""))
}
