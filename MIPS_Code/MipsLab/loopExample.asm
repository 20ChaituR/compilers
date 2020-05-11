# Exercise 6 in the MIPS Lab
# Reads in three numbers, representing low, high, and step size.
# Prints the numbers from low to high with a separation of step size.

   .text 0x00400000
   .globl main 
main:
   la $a0, intro       # Prints intro
   li $v0, 4
   syscall

   li $v0, 5           # Read user input and put into t0
   syscall
   move $t0, $v0
   
   li $v0, 5           # Read user input and put into t1
   syscall
   move $t1, $v0
   
   li $v0, 5           # Read user input and put into t2
   syscall
   move $t2, $v0

   loop:
      bgt $t0, $t1, endloop    # While t0 <= t1
      
      move $a0, $t0            # Print t0
      li $v0, 1
      syscall
      
      la $a0, sep              # Print separator
      li $v0, 4
      syscall
      
      addu $t0, $t0, $t2       # t0 = t0 + t2
      j loop
   endloop:
   
   li $v0, 10          # End program
   syscall


   .data
intro:
   .asciiz "Enter the low, high, and step size for the loop:\n"
sep:
   .asciiz " "
