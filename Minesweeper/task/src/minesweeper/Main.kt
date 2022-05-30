package minesweeper
// import java.util.Random

class Minefield(val raw: Int, val column: Int, var numberOfMines: Int = 0) {
    
    val field = MutableList<MutableList<String>>(this.raw) {
        MutableList<String>(this.column) { "." }
    }
    val MinesCoordinate = emptyList<MutableList<Int>>().toMutableList()
    var playerCoordinate = emptyList<MutableList<Int>>().toMutableList()
    val playersField = MutableList<MutableList<String>>(this.raw) {
        MutableList<String>(this.column) { "." }
    }
    var faile = false

    init {
        println("How many mines do you want on the field? ")
        this.numberOfMines = readLine()!!.toInt()
        println()
        addMines()
        addNumberOfMines()
        //hideMines()
        printField(playersField)
        playerCoordinate = MinesCoordinate

        while (playerCoordinate.size != 0) {
            println("Set/unset mines marks or claim a cell as free:")
            val playersMove = readln().split(" ")
            println()
            
            val xColumn = playersMove[0].toInt() - 1
            val  yRaw = playersMove[1].toInt() - 1
            val action = playersMove[2]
            
            val playersMoveXY = mutableListOf(yRaw, xColumn)
            if (playersMoveXY in playerCoordinate && action == "mine") playerCoordinate.remove(playersMoveXY)
            else if (playersMoveXY !in playerCoordinate && action == "mine") playerCoordinate.add(playersMoveXY)

            playersAction(action, yRaw, xColumn)


        }
        if (faile == true) println("You stepped on a mine and failed!")
        else println("Congratulations! You found all the mines!")
    }

    fun addMines() {
        var count = 0
        while (count != this.numberOfMines) {
            count++
            //var raw = Random().nextInt(0, this.raw)
            val raw = (0..this.raw - 1).random()
            val column = (0..this.column - 1).random()
            if (field[column][raw] == "X") count--
            field[column][raw] = "X"
            MinesCoordinate.add(mutableListOf(column, raw))
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
    
    fun playersAction(action: String, xColumn: Int, yRaw: Int) {
        when (action) {
            "mine" -> {
                if (playersField[xColumn][yRaw] == ".") playersField[xColumn][yRaw] = "*"
                else playersField[xColumn][yRaw] = "."
                printField(playersField)
            }
            "free" -> {
                if (field[xColumn][yRaw] == "X" && action == "free") {
                    for (i in MinesCoordinate) playersField[i[0]][i[1]] = "X"
                    printField(playersField)
                    playerCoordinate.clear()
                    faile = true
                }
                else if (field[yRaw][xColumn] != ".") {
                    playersField[yRaw][xColumn] = field[yRaw][xColumn]
                    //printField(field)
                    printField(playersField)
                }
                else if (field[yRaw][xColumn] == ".") {
                    playersField[yRaw][xColumn] = "/"
                    var col = xColumn
                    var raw = yRaw

                    for (i in col..8) {
                        col = i
                        while(raw in 0..7 && field[raw++][col] == ".") {
                            playersField[raw][col] = "/"
                            //printField(field)
                            //printField(playersField)
                        }
                        raw = xColumn
                        while(raw in 1..8 && field[raw--][col] == ".") {
                            playersField[raw][col] = "/"
                            //printField(field)
                            //printField(playersField)
                        }
                    }
                    col = yRaw
                    for (i in col downTo 0) {
                        col = i
                        if (col in 0..8) {
                            while(field[raw++][col] == "." && raw in 0..7) {
                                playersField[raw][col] = "/" 
                            }
                            raw = xColumn
                            while(field[raw--][col] == "." && raw in 1..8) {
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

    fun exit() {
        return
    }

}

fun main() {
    
    val minesweeper = Minefield(9, 9)
    
}
