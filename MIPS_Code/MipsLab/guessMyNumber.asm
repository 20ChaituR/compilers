# Guess My Number
# This program consists of two games: one where the user has to guess 
# the computer's number, and one where the computer has to guess the 
# user's number.

   .text 0x00400000
   .globl main 
main:
   la $a0, intro       # Prints intro
   li $v0, 4
   syscall
   
   li $v0, 5           # Reads user input
   syscall
   
   beq $v0, 0, pick    # If input was 0, pick a number, else guess user's number
   guess:
      la $a0, guessIntro      # Introduces game
      li $v0, 4
      syscall
      
      li $t1, 1               # l = 1
      li $t2, 100             # r = 100
      
      li $t5, 0               # t5 = num of guesses

      guessLoop:
         bgt $t1, $t2, endGuessLoop
         
         addu $t3, $t1, $t2
         divu $t3, $t3, 2     # m = (l + r) / 2
         
         la $a0, guessMessage
         li $v0, 4
         syscall
         
         move $a0, $t3
         li $v0, 1
         syscall
         
         la $a0, guessMessageEnd   # Prints my guess for the number
         li $v0, 4
         syscall
         
         addu $t5, $t5, 1          # t5 = t5 + 1
         
         li $v0, 5                 # Reads whether guess is too big or small
         syscall
         move $t0, $v0
         
         beq $t0, 0, guessCorrect
         beq $t0, 1, guessTooSmall
         beq $t0, 2, guessTooBig
            j guessLoop
         guessCorrect:             # If t0 = 0, prints win message and exits
            la $a0, guessWin
            li $v0, 4
            syscall
            
            move $a0, $t5
            li $v0, 1
            syscall
            
            la $a0, guessWinEnd
            li $v0, 4
            syscall
            
            j afterGame
         guessTooSmall:            # If t0 = 1, sets l to m + 1
            addu $t1, $t3, 1
            j guessLoop
         guessTooBig:              # If t0 = 2, sets r to m - 1
            subu $t2, $t3, 1
            j guessLoop
            
      endGuessLoop:
      
      la $a0, guessFail   # Prints failure message if I couldn't guess
      li $v0, 4
      syscall
      
      j afterGame
   pick:
      li $t0 43      # My number is 43
                     # TODO: make this pseudorandom
      
      pickLoop:
         beq $t0, $t1, endPickLoop
         
         la $a0, pickMessage      # Asks user to guess a number
         li $v0, 4
         syscall
         
         li $v0, 5                # Reads user guess
         syscall
         move $t1, $v0
         
         bgt $t1, 100, pickLoop   # Makes sure input is between 1 and 100
         blt $t1, 1, pickLoop
         
         bgt $t1, $t0, pickTooBig
         blt $t1, $t0, pickTooSmall
            j pickLoop
         pickTooSmall:
            la $a0, pickGreater       # If t1 < t0, prints too small
            li $v0, 4
            syscall
            j pickLoop
         pickTooBig:
            la $a0, pickLess          # If t1 > t0, prints too big
            li $v0, 4
            syscall
            j pickLoop
      endPickLoop:
      
      la $a0, pickCongrats      # Prints win message
      li $v0, 4
      syscall
   
   afterGame:
      li $v0, 10 
      syscall             # End program


   .data
intro:
   .asciiz "Type 0 if you want to guess my number, type 1 if you want me to guess:\n"
   
pickMessage:
   .asciiz "Guess a number between 1 and 100:\n"
pickGreater:
   .asciiz "Too small!\n"
pickLess:
   .asciiz "Too big!\n"
pickCongrats:
   .asciiz "You got it!\n"
   
guessIntro:
   .asciiz "Pick a number between 1 and 100. I'll try to guess it!\n"
guessMessage:
   .asciiz "Is your number "
guessMessageEnd:
   .asciiz "? (Type 0 if I got it, 1 if my guess was too small, 2 if it was too big)\n"
guessWin:
   .asciiz "Wow, I got it in only "
guessWinEnd:
   .asciiz " guesses!\n"
guessFail:
   .asciiz "You're not very smart, are you?\n"
