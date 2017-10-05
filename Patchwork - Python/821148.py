#-------------------------------------------------------------------------------
# Python Coursework: A Patchwork Sampler
# Tony Van  
# UP821148
#-------------------------------------------------------------------------------

from graphics import * 
import math

#Constants of colours allowed and the accepted patch sizes
coloursAllowed = ["red", "green", "blue", "orange", "brown", "pink"]
patchSizes = [5,7,9]

def main():
    patchWorkSize, windowSize, colourOrder = getInputs()
    win, colourList, patchList = drawPatchWork(
    patchWorkSize, windowSize, colourOrder)
    changeColours(win, patchWorkSize, colourList, patchList, colourOrder)
    
#Get patch work size and colour inputs from user
def getInputs():
    isValid = True
    colourOrder = []
    validatedPatch = 0
    currentColour = ""
    
    patchWorkSize = input("Enter size of patchwork (5,7,9): ")
    
    while isValid:
        if patchWorkSize.isdigit() == False:
            print("Please enter a digit")
            patchWorkSize = input("Enter size of patchwork (5,7,9): ")
        else:
            validation = int(patchWorkSize)
            if validation not in patchSizes:
                print("Wrong patch size")
                patchWorkSize = input("Enter size of patchwork (5,7,9): ")
            else:
                validatedPatch = validation
                break
    
    for i in range(3):
        colour = input(
        "Enter colours (red, green, blue, orange, brown, pink): ")
        while isValid == True:
            if colour not in coloursAllowed:
                print("Not an appropriate colour")
                colour = input(
                "Enter colours (red, green, blue, orange, brown, pink): ")
            elif currentColour == colour:
                print("Colour must not be the same")
                colour = input(
                "Enter colours (red, green, blue, orange, brown, pink): ")
            else:
                colourOrder.append(colour)
                currentColour  = colour
                break
                
    return validatedPatch, validatedPatch * 100, colourOrder

#Draw patchwork with allocated size and colours :- 1
def drawPatchWork(patchWorkSize, winSize, colours):
    win = GraphWin("Patchwork Sampler:- 821148", winSize, winSize)
    win.setBackground("white")

    colourList = [[]]
    patchList = [[]]

    patchesAreDrawing = True
    
    rowValue = 0
    columnValue = 0
    
    #Set middle colour on patch work
    middleColourX = winSize - 100
    middleColourY = 0
    
    #Final patch columns
    finalPatch = [patchWorkSize-1]
    finalPatchColumn = patchWorkSize-1
    
    colourPick = 0
    x = 0
    y = 0
    
    while patchesAreDrawing:
        #Break out while loop when all patches are drawn
        if columnValue == patchWorkSize:
            break
        
        #Reset row and move down in column
        elif rowValue == patchWorkSize:
            y += 100
            x = 0
            rowValue = 0
            columnValue += 1

            finalPatchColumn -= 1
            middleColourX -= 100
            middleColourY += 100
            
            #Modulo to make sure that the right columns contain the final patch
            if finalPatchColumn%2 == 0 or finalPatchColumn == 1:
                finalPatch.append(finalPatchColumn)
            #Makes sure extra list is not added
            if columnValue != patchWorkSize:
                colourList.append([])
                patchList.append([])
        
        else:
            #set colours depending if values are smaller or higher than 
            #middle row
            
            if x == middleColourX and y == middleColourY:
                colourPick = 1
            elif x <= middleColourX and y <= middleColourY:
                colourPick = 0
            else:
                colourPick = 2
            #Makes final patch draw when row value % 2 = 0 and if the row value
            #is in the list
            if rowValue%2 == 0 and rowValue in finalPatch:
                drawPatchF(win, x, y, colours[colourPick])
                colourList[columnValue].append(colourPick)
                patchList[columnValue].append("f")
                x += 100
                rowValue += 1
            else:
                drawPatchP(win, x, y, colours[colourPick])
                colourList[columnValue].append(colourPick)
                patchList[columnValue].append("p")
                x += 100
                rowValue += 1
                
    return win, colourList, patchList

# #Draw final digit patch :- 8
def drawPatchF(win, x, y, colours):
    
    x2 = x + 100
    y2 = y + 100
    
    for i in range(10):
      
        point1 = Point(x,y)
        point2 = Point(x2,y2)
        square = Rectangle(point1, point2)
        square.setWidth(0)
        #modulo to make sure next square is set to white
        if i%2 == 0:
            square.setFill(colours)
        else:
            square.setFill("white")
        
        square.draw(win)

        x += 5
        y += 5
        x2 -= 5
        y2 -= 5
       
#Draw penultimate digit patch :- 4
def drawPatchP(win, xStart, yStart, colours):
    
    border = Rectangle(Point(xStart,yStart),Point(xStart+100,yStart+100))
    border.draw(win)
    
    x = xStart
    y = yStart
    whiteRow = 1
    whiteColumn = 1
    blankColumn = 0
    
    
    for column in range(9):
        for circleSquare in range(3):
            for rows in range(3):
                circle = Circle(Point(x+5,y+5),5)
                circle.setFill(colours)
                circle.draw(win)
                x += 10
                whiteRow += 1
                #modulo division by 3 to make sure 2nd square is always a white
                if (whiteRow)% 3 == 0 and (whiteColumn)% 3 == 0:
                    circle.setFill("white")  
          
        
            x += 5
        #draws another set of 3 circles
        whiteColumn += 2
        blankColumn += 1
        #After drawing 3 squares down, it adds a gap
        if blankColumn% 3 == 0:
            y += 5
        #Resets back to start in the next column
        x = xStart
        y += 10    

def changeColours(win, patchWorkSize, colourList, patchList, colours):
    userClicks = True
    rowColourList = []
    rowPatchList = []
    newColour = 0
    p = win.getMouse()
    pX = p.getX()
    pY = p.getY()
    
    while userClicks:
        pX -= 50
        pY -= 50
        newX = round(pX,-2) 
        newY = round(pY,-2)
        
#find the right colour and patch through mouse click
        rowColourList = colourList[int(newY/100)]
        currentColour = rowColourList[int(newX/100)]
        
        rowPatchList = patchList[int(newY/100)]
        currentPatch = rowPatchList[int(newX/100)]
        
        if currentColour == 2:
            newColour = 0
        else:
            newColour = currentColour + 1
#change currentcolour to next colour        
        if currentPatch == "p":
            drawPatchP(win, newX, newY, colours[newColour])
        else:
            drawPatchF(win, newX, newY, colours[newColour])

#insert new colour back into the array
        rowColourList[int(newX/100)] = newColour
        colourList[int(newY/100)] = rowColourList
        
        p = win.getMouse()
        pX = p.getX()
        pY = p.getY()
                
main()