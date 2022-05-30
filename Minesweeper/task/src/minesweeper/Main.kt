package minesweeper
import java.util.Random

class Minefield(val raw: Int, val column: Int, var numberOfMines: Int = 0) {
    
    val field = MutableList<MutableList<String>>(this.raw) {
        MutableList<String>(this.column) { "." }
    }
    val MinesCoordinate = emptyList<MutableList<Int>>().toMutableList()
    var playerCoordinate = emptyList<MutableList<Int>>().toMutableList()
    val playersField = MutableList<MutableList<String>>(this.raw) {
        MutableList<String>(this.column) { "." }
    }

    init {
        println("How many mines do you want on the field? ")
        this.numberOfMines = readLine()!!.toInt()
        println()
        addMines()
        addNumberOfMines()
        //hideMines()
        printField(playersField)


        //var playerCoordinate = MinesCoordinate.toMutableList()
        playerCoordinate = MinesCoordinate

        while (playerCoordinate.size != 0) {
            println("Set/unset mines marks or claim a cell as free:")
            //var playersMove = readln().split(" ").map { it.isDigit() -> it.toInt() }.toMutableList()
            val playersMove = readln().split(" ")
            println()
            
            val y = playersMove[0].toInt() - 1
            val x = playersMove[1].toInt() - 1
            val action = playersMove[2]
            
            val playersMoveXY = mutableListOf(x, y)
            //println(MinesCoordinate)
            //println(playersMove)
            if (playersMoveXY in playerCoordinate && action == "mine") playerCoordinate.remove(playersMoveXY)
            else playerCoordinate.add(playersMoveXY)

            //if (field[x][y] != "*") field[x][y] = "*"
            //else field[x][y] = "."
            //printField(playersField)
            playersAction(action, x, y)
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

    fun printField(fiel: MutableList<MutableList<String>>) {
        val word = (1..this.column).toList()
        println(" │${word.joinToString("")}│")

        print("—│")
        for (i in 1..this.column) print("—")
        print("│")
        println()
        var count = 1
        for (i in fiel) {
            println("${count++}│${i.joinToString("")}│")
        }
        print("—│")
        for (i in 1..this.column) print("—")
        print("│")
        println()
    }
    
    fun playersAction(action: String, x: Int, y: Int) {
        when (action) {
            "mine" -> {
                if (playersField[x][y] == ".") playersField[x][y] = "*"
                else playersField[x][y] = "."
                printField(playersField)
            }
            "free" -> {
                if (field[x][y] == "X") {
                    for (i in MinesCoordinate) playersField[i[0]][i[1]] = "X"
                    printField(playersField)
                    println("You stepped on a mine and failed!")
                }
                else if (field[x][y] != ".") {
                    playersField[x][y] = field[x][y]
                    //printField(field)
                    printField(playersField)
                }
                else if (field[x][y] == ".") {
                    playersField[x][y] = "/"
                    var col = y
                    var raw = x
                    for (i in col..8) {
                        col = i
                        if (col in 0..8) {
                            while(field[raw++][col] == "." && raw in 0..8) {
                                playersField[raw][col] = "/" 
                            }
                            raw = x                    
                            while(field[raw--][col] == "." && raw in 0..8) {
                                playersField[raw][col] = "/" 
                            }
                        }
                        
                    }
                    
                    for (i in col downTo 0) {
                        col = i
                        if (col in 0..8) {
                            while(field[raw++][col] == "." && raw in 0..8) {
                                playersField[raw][col] = "/" 
                            }
                            raw = x                    
                            while(field[raw--][col] == "." && raw in 0..8) {
                                playersField[raw][col] = "/" 
                            }
                        }
                        
                    }
                    
                    printField(field)
                    printField(playersField)
                }  
            }
        }
    }

}

fun main() {
    
    val minesweeper = Minefield(9, 9)
    
}
