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

    fun playersAction(action: String, yRaw: Int, xColumn: Int) {
        when (action) {
            "mine" -> {
                if (playersField[yRaw][xColumn] == ".") {
                    playersField[yRaw][xColumn] = "*"
                }

                else {
                    playersField[yRaw][xColumn] = "."
                }
                printField(playersField)
            }
            "free" -> {
                if (field[yRaw][xColumn] == "X") {
                    for (i in MinesCoordinate) playersField[i[0]][i[1]] = "X"
                    printField(playersField)
                    playerCoordinate.clear()
                    faile = true
                }
                else if (field[yRaw][xColumn] != ".") {
                    playersField[yRaw][xColumn] = field[yRaw][xColumn]
                    printField(playersField)
                }
                else if (field[yRaw][xColumn] == ".") {
                    floodfill(field, yRaw, xColumn)
                    printField(playersField)
                }
            }
        }

    }

    fun floodfill(matrix: MutableList<MutableList<String>>, x: Int, y: Int) {
        if (matrix[x][y] == ".") {
            matrix[x][y] = "/"
            playersField[x][y] = "/"
            if(x > 0) floodfill(matrix,x-1, y)
            if(x > 0 && y > 0) floodfill(matrix,x-1, y-1) // 1
            if (x < matrix[y].size -1 && y < matrix.size - 1) floodfill(matrix,x+1,y+1) //2
            if (x > 0 && y < matrix.size - 1) floodfill(matrix,x-1,y+1) //3
            if (x < matrix[y].size -1 && y > 0) floodfill(matrix,x+1,y-1) //2
            if (x < matrix[y].size -1) floodfill(matrix,x+1,y)
            if (y > 0) floodfill(matrix,x,y-1)
            if (y < matrix.size - 1) floodfill(matrix,x,y+1)
        }
        else playersField[x][y] = matrix[x][y]

    }

}

fun main() {

    val minesweeper = Minefield(9, 9)

}
