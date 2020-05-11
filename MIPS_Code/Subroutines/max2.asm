# Gets the maximum of two user inputted numbers using a subroutine
# @author Chaitu Ravuri
# @version 5/1/2020
    
    .text
    .globl main
main:
    li $v0 5               # Reads a number and puts it into a0
    syscall
    move $a0 $v0
    
    li $v0 5               # Reads a number and puts it into a1
    syscall
    move $a1 $v0

    subu $sp, $sp, 4       # Pushes ra to the stack
    sw $ra, ($sp)
    
    jal max2               # Calls max2
    
    lw $ra, ($sp)          # Pops the stack onto ra
    addu $sp, $sp, 4
    
    move $a0 $v0           # Prints v0
    li $v0, 1
    syscall
    
    li $v0, 10             # Ends program
    syscall

# Subroutine to find the maximum of two numbers
max2:
    bgt $a0 $a1 if1        # Jumps to if1 if a0 > a1
    
    move $v0 $a1           # Returns a1
    jr $ra
    
    if1:
    move $v0 $a0           # Returns a0
    jr $ra

    .data