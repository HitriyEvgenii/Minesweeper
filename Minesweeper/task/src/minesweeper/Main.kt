package minesweeper
import java.util.Random

class Minefield(val raw: Int, val column: Int, val numberOfMines: Int = 0) {
    val field = MutableList<MutableList<String>>(this.raw) {
        MutableList<String>(this.column) { "." }
    }
    val MinesCoordinate = emptyList<MutableList<Int>>().toMutableList()
    var playerCoordinate = emptyList<MutableList<Int>>().toMutableList()

    init {
        addMines()
        addNumberOfMines()
        hideMines()

        printField()


        //var playerCoordinate = MinesCoordinate.toMutableList()
        playerCoordinate = MinesCoordinate

        while (playerCoordinate.size != 0) {
            println("Set/delete mines marks (x and y coordinates):")
            var playersMove = readln().split(" ").map { it.toInt() }.toMutableList()
            playersMove[0]--
            playersMove[1]--
            //println(MinesCoordinate)
            //println(playersMove)
            if (playersMove in MinesCoordinate) playerCoordinate.remove(playersMove)
            else playerCoordinate.add(playersMove)
            field[playersMove[0]][playersMove[1]] = "X"
            printField()
        }
        println("Congratulations! You found all the mines!")


    }

    fun addMines() {
        var count = 0
        while (count != this.numberOfMines) {
            count++
            //var raw = Random().nextInt(0, this.raw)
            var raw = (0..this.raw - 1).random()
            var column = (0..this.column - 1).random()
            if (field[raw][column] == "X") count--
            field[raw][column] = "X"
            MinesCoordinate.add(mutableListOf(raw, column))
        }

    }

    fun addNumberOfMines() {
        for (raw in 0..this.raw - 1) {
            for (column in 0..this.column - 1) {
                if (this.field[raw][column] == "X") continue
                else {
                    var count = 0
                    if (raw in 1 until this.raw - 1) {
                        when {
                            column in 1 until this.raw - 1 -> {
                                if (this.field[raw - 1][column - 1] == "X") count++
                                if (this.field[raw - 1][column] == "X") count++
                                if (this.field[raw - 1][column + 1] == "X") count++
                                if (this.field[raw][column - 1] == "X") count++
                                if (this.field[raw][column + 1] == "X") count++
                                if (this.field[raw + 1][column - 1] == "X") count++
                                if (this.field[raw + 1][column] == "X") count++
                                if (this.field[raw + 1][column + 1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                            column == 0 -> {
                                if (this.field[raw - 1][column] == "X") count++
                                if (this.field[raw - 1][column + 1] == "X") count++
                                if (this.field[raw][column + 1] == "X") count++
                                if (this.field[raw + 1][column] == "X") count++
                                if (this.field[raw + 1][column + 1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                            column == this.column - 1 -> {
                                if (this.field[raw - 1][column] == "X") count++
                                if (this.field[raw - 1][column - 1] == "X") count++
                                if (this.field[raw][column - 1] == "X") count++
                                if (this.field[raw + 1][column] == "X") count++
                                if (this.field[raw + 1][column - 1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }

                        }

                    } else if (raw == 0) {
                        when {
                            column in 1 until this.column - 1 -> {
                                if (this.field[raw][column - 1] == "X") count++
                                if (this.field[raw][column + 1] == "X") count++
                                if (this.field[raw + 1][column - 1] == "X") count++
                                if (this.field[raw + 1][column] == "X") count++
                                if (this.field[raw + 1][column + 1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                            column == 0 -> {
                                if (this.field[raw][column + 1] == "X") count++
                                if (this.field[raw + 1][column] == "X") count++
                                if (this.field[raw + 1][column + 1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                            column == this.column - 1 -> {
                                if (this.field[raw][column - 1] == "X") count++
                                if (this.field[raw + 1][column] == "X") count++
                                if (this.field[raw + 1][column - 1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                        }

                    } else if (raw == this.raw - 1) {
                        when {
                            column in 1 until this.column - 1 -> {
                                if (this.field[raw][column - 1] == "X") count++
                                if (this.field[raw][column + 1] == "X") count++
                                if (this.field[raw - 1][column - 1] == "X") count++
                                if (this.field[raw - 1][column] == "X") count++
                                if (this.field[raw - 1][column + 1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                            column == 0 -> {
                                if (this.field[raw][column + 1] == "X") count++
                                if (this.field[raw - 1][column] == "X") count++
                                if (this.field[raw - 1][column + 1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                            column == this.column - 1 -> {
                                if (this.field[raw][column - 1] == "X") count++
                                if (this.field[raw - 1][column] == "X") count++
                                if (this.field[raw - 1][column - 1] == "X") count++
                                if (count != 0) this.field[raw][column] = count.toString()
                            }
                        }
                    }
                }

            }
        }
    }

    fun addNumberOfMines2() {
        for (coordinate in MinesCoordinate) {
            var count = 0
            val raw = coordinate[0]
            val column = coordinate[1]
            /* if (raw in 1 until this.raw - 1 && column in 1 until this.column - 1) {
                if (this.field[raw - 1][column] == ".") this.field[raw - 1][column] = "1"
                else if (this.field[raw - 1][column] != "." && this.field[raw - 1][column] != "X") {
                    this.field[raw - 1][column] = (this.field[raw - 1][column].toInt() + 1).toString()
                }
                if (this.field[raw - 1][column-1] == ".") this.field[raw - 1][column-1] = "1"
                else if (this.field[raw - 1][column-1] != "." && this.field[raw - 1][column-1] != "X") {
                    this.field[raw - 1][column-1] = (this.field[raw - 1][column-1].toInt() + 1).toString()
                }
                if (this.field[raw - 1][column+1] == ".") this.field[raw - 1][column+1] = "1"
                else if (this.field[raw - 1][column+1] != "." && this.field[raw - 1][column+1] != "X") {
                    this.field[raw - 1][column+1] = (this.field[raw - 1][column+1].toInt() + 1).toString()
                }
                if (this.field[raw][column+1] == ".") this.field[raw][column+1] = "1"
                else if (this.field[raw][column+1] != "." && this.field[raw][column+1] != "X") {
                    this.field[raw][column+1] = (this.field[raw][column+1].toInt() + 1).toString()
                }
                if (this.field[raw][column-1] == ".") this.field[raw][column-1] = "1"
                else if (this.field[raw][column-1] != "." && this.field[raw][column-1] != "X") {
                    this.field[raw][column-1] = (this.field[raw][column-1].toInt() + 1).toString()
                }
                if (this.field[raw + 1][column] == ".") this.field[raw + 1][column] = "1"
                else if (this.field[raw + 1][column] != "." && this.field[raw + 1][column] != "X") {
                    this.field[raw + 1][column] = (this.field[raw + 1][column].toInt() + 1).toString()
                }
                if (this.field[raw + 1][column-1] == ".") this.field[raw + 1][column-1] = "1"
                else if (this.field[raw + 1][column-1] != "." && this.field[raw + 1][column-1] != "X") {
                    this.field[raw + 1][column-1] = (this.field[raw + 1][column-1].toInt() + 1).toString()
                }
                if (this.field[raw + 1][column+1] == ".") this.field[raw + 1][column+1] = "1"
                else if (this.field[raw + 1][column+1] != "." && this.field[raw + 1][column+1] != "X") {
                    this.field[raw + 1][column+1] = (this.field[raw + 1][column+1].toInt() + 1).toString()
                }

            } */
            if (raw == 0 && column in 1 until this.column - 1) {
                if (this.field[raw][column+1] == ".") this.field[raw][column+1] = "1"
                else if (this.field[raw][column+1] != "X") {
                    this.field[raw][column+1] = (this.field[raw][column+1].toInt() + 1).toString()
                }
                if (this.field[raw][column-1] == ".") this.field[raw][column-1] = "1"
                else if (this.field[raw][column-1] != "X") {
                    this.field[raw][column-1] = (this.field[raw][column-1].toInt() + 1).toString()
                }
                if (this.field[raw + 1][column] == ".") this.field[raw + 1][column] = "1"
                else if (this.field[raw + 1][column] != "X") {
                    this.field[raw + 1][column] = (this.field[raw + 1][column].toInt() + 1).toString()
                }
                if (this.field[raw + 1][column-1] == ".") this.field[raw + 1][column-1] = "1"
                else if (this.field[raw + 1][column-1] != "X") {
                    this.field[raw + 1][column-1] = (this.field[raw + 1][column-1].toInt() + 1).toString()
                }
                if (this.field[raw + 1][column+1] == ".") this.field[raw + 1][column+1] = "1"
                else if (this.field[raw + 1][column+1] != "X") {
                    this.field[raw + 1][column+1] = (this.field[raw + 1][column+1].toInt() + 1).toString()
                }
            }
            else if (raw == this.raw-1 && column in 1 until this.column - 1) {
                if (this.field[raw - 1][column] == ".") this.field[raw - 1][column] = "1"
                else if (this.field[raw - 1][column] != "X") {
                    this.field[raw - 1][column] = (this.field[raw - 1][column].toInt() + 1).toString()
                }
                if (this.field[raw - 1][column-1] == ".") this.field[raw - 1][column-1] = "1"
                else if (this.field[raw - 1][column-1] != "X") {
                    this.field[raw - 1][column-1] = (this.field[raw - 1][column-1].toInt() + 1).toString()
                }
                if (this.field[raw - 1][column+1] == ".") this.field[raw - 1][column+1] = "1"
                else if (this.field[raw - 1][column+1] != "X") {
                    this.field[raw - 1][column+1] = (this.field[raw - 1][column+1].toInt() + 1).toString()
                }
                if (this.field[raw][column+1] == ".") this.field[raw][column+1] = "1"
                else if (this.field[raw][column+1] != "X") {
                    this.field[raw][column+1] = (this.field[raw][column+1].toInt() + 1).toString()
                }
                if (this.field[raw][column-1] == ".") this.field[raw][column-1] = "1"
                else if (this.field[raw][column-1] != "X") {
                    this.field[raw][column-1] = (this.field[raw][column-1].toInt() + 1).toString()
                }
            }
            else if (column == 0 && raw in 1 until this.raw - 1) {
                if (this.field[raw-1][column] == ".") this.field[raw-1][column] = "1"
                else if (this.field[raw-1][column] != "X") {
                    this.field[raw-1][column] = (this.field[raw-1][column].toInt() + 1).toString()
                }
                if (this.field[raw-1][column+1] == ".") this.field[raw-1][column+1] = "1"
                else if (this.field[raw-1][column+1] != "X") {
                    this.field[raw-1][column+1] = (this.field[raw-1][column+1].toInt() + 1).toString()
                }
                if (this.field[raw][column+1] == ".") this.field[raw][column+1] = "1"
                else if (this.field[raw][column+1] != "X") {
                    this.field[raw][column+1] = (this.field[raw][column+1].toInt() + 1).toString()
                }
                if (this.field[raw+1][column+1] == ".") this.field[raw+1][column+1] = "1"
                else if (this.field[raw+1][column+1] != "X") {
                    this.field[raw+1][column+1] = (this.field[raw+1][column+1].toInt() + 1).toString()
                }
                if (this.field[raw+1][column] == ".") this.field[raw+1][column] = "1"
                else if (this.field[raw+1][column] != "X") {
                    this.field[raw+1][column] = (this.field[raw+1][column].toInt() + 1).toString()
                }
            }
            else if (column == this.column-1 && raw in 1 until this.raw - 1) {
                if (this.field[raw-1][column] == ".") this.field[raw-1][column] = "1"
                else if (this.field[raw-1][column] != "X") {
                    this.field[raw-1][column] = (this.field[raw-1][column].toInt() + 1).toString()
                }
                if (this.field[raw-1][column-1] == ".") this.field[raw-1][column-1] = "1"
                else if (this.field[raw-1][column-1] != "X") {
                    this.field[raw-1][column-1] = (this.field[raw-1][column-1].toInt() + 1).toString()
                }
                if (this.field[raw][column-1] == ".") this.field[raw][column-1] = "1"
                else if (this.field[raw][column-1] != "X") {
                    this.field[raw][column-1] = (this.field[raw][column-1].toInt() + 1).toString()
                }
                if (this.field[raw+1][column-1] == ".") this.field[raw+1][column-1] = "1"
                else if (this.field[raw+1][column-1] != "X") {
                    this.field[raw+1][column-1] = (this.field[raw+1][column-1].toInt() + 1).toString()
                }
                if (this.field[raw+1][column] == ".") this.field[raw+1][column] = "1"
                else if (this.field[raw+1][column] != "X") {
                    this.field[raw+1][column] = (this.field[raw+1][column].toInt() + 1).toString()
                }
            }
            else if (raw == 0 && column == 0) {
                if (this.field[raw][column+1] == ".") this.field[raw][column+1] = "1"
                else if (this.field[raw][column+1] != "X") {
                    this.field[raw][column+1] = (this.field[raw][column+1].toInt() + 1).toString()
                }
                if (this.field[raw+1][column+1] == ".") this.field[raw+1][column+1] = "1"
                else if (this.field[raw+1][column+1] != "X") {
                    this.field[raw+1][column+1] = (this.field[raw+1][column+1].toInt() + 1).toString()
                }
                if (this.field[raw+1][column] == ".") this.field[raw+1][column] = "1"
                else if (this.field[raw+1][column] != "X") {
                    this.field[raw+1][column] = (this.field[raw+1][column].toInt() + 1).toString()
                }

            }
            else if (raw == 0 && column == this.column-1) {
                if (this.field[raw][column-1] == ".") this.field[raw][column-1] = "1"
                else if (this.field[raw][column-1] != "X") {
                    this.field[raw][column-1] = (this.field[raw][column-1].toInt() + 1).toString()
                }
                if (this.field[raw+1][column-1] == ".") this.field[raw+1][column-1] = "1"
                else if (this.field[raw+1][column-1] != "X") {
                    this.field[raw+1][column-1] = (this.field[raw+1][column-1].toInt() + 1).toString()
                }
                if (this.field[raw+1][column] == ".") this.field[raw+1][column] = "1"
                else if (this.field[raw+1][column] != "X") {
                    this.field[raw+1][column] = (this.field[raw+1][column].toInt() + 1).toString()
                }

            }
            else if (raw == this.raw-1 && column == 0) {
                if (this.field[raw][column+1] == ".") this.field[raw][column+1] = "1"
                else if (this.field[raw][column+1] != "X") {
                    this.field[raw][column+1] = (this.field[raw][column+1].toInt() + 1).toString()
                }
                if (this.field[raw-1][column+1] == ".") this.field[raw-1][column+1] = "1"
                else if (this.field[raw-1][column+1] != "X") {
                    this.field[raw-1][column+1] = (this.field[raw-1][column+1].toInt() + 1).toString()
                }
                if (this.field[raw-1][column] == ".") this.field[raw-1][column] = "1"
                else if (this.field[raw-1][column] != "X") {
                    this.field[raw-1][column] = (this.field[raw-1][column].toInt() + 1).toString()
                }

            }
            else if (raw == this.raw-1 && column == this.column-1) {
                if (this.field[raw][column-1] == ".") this.field[raw][column-1] = "1"
                else if (this.field[raw][column-1] != "X") {
                    this.field[raw][column-1] = (this.field[raw][column-1].toInt() + 1).toString()
                }
                if (this.field[raw-1][column-1] == ".") this.field[raw-1][column-1] = "1"
                else if (this.field[raw-1][column-1] != "X") {
                    this.field[raw-1][column-1] = (this.field[raw-1][column-1].toInt() + 1).toString()
                }
                if (this.field[raw-1][column] == ".") this.field[raw-1][column] = "1"
                else if (this.field[raw-1][column] != "X") {
                    this.field[raw-1][column] = (this.field[raw-1][column].toInt() + 1).toString()
                }

            }
        }
    }

    fun hideMines() {
        for (coordinate in MinesCoordinate) field[coordinate[0]][coordinate[1]] = "."
    }

    fun printField() {
        val word = (1..this.column).toList()
        println(" |${word.joinToString("")}|")

        print("-|")
        for (i in 1..this.column) print("-")
        print("|")
        println()
        var count = 1
        for (i in this.field) {
            println("${count++}|${i.joinToString("")}|")
        }
        print("-|")
        for (i in 1..this.column) print("-")
        print("|")
        println()
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
