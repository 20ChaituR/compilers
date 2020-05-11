# Gets the nth fibonacci number using recursion
# @author Chaitu Ravuri
# @version 5/1/2020
    
    .text
    .globl main
main:
    li $v0 5               # Reads a number and puts it into a0
    syscall
    move $a0 $v0

    subu $sp, $sp, 4       # Pushes ra to the stack
    sw $ra, ($sp)
    
    jal fib                # Calls fib
    
    lw $ra, ($sp)          # Pops the stack onto ra
    addu $sp, $sp, 4
    
    move $a0 $v0           # Prints v0
    li $v0, 1
    syscall
    
    li $v0, 10             # Ends program
    syscall
    
fib:
    ble $a0 1 if1          # Goes to if1 if a0 <= 1
    
    subu $sp, $sp, 4       # Pushes ra to the stack
    sw $ra, ($sp)
    
    subu $sp, $sp, 4       # Pushes a0 to the stack
    sw $a0, ($sp)
    
    subu $a0, $a0, 1       # a0 = a0 - 1
    
    jal fib                # Calls fib
    
    lw $a0, ($sp)          # Pops the stack onto a0
    addu $sp, $sp, 4
    
    lw $ra, ($sp)          # Pops the stack onto ra
    addu $sp, $sp, 4
    
    subu $sp, $sp, 4       # Pushes ra to the stack
    sw $ra, ($sp)
    
    subu $sp, $sp, 4       # Pushes a0 to the stack
    sw $a0, ($sp)
    
    subu $sp, $sp, 4       # Pushes v0 to the stack
    sw $v0, ($sp)
    
    subu $a0, $a0, 2       # a0 = a0 - 1
    
    jal fib                # Calls fib
    
    lw $t0, ($sp)          # Pops the stack onto t0
    addu $sp, $sp, 4
    
    lw $a0, ($sp)          # Pops the stack onto a0
    addu $sp, $sp, 4
    
    lw $ra, ($sp)          # Pops the stack onto ra
    addu $sp, $sp, 4
    
    addu $v0, $v0, $t0     # Returns v0 + t0
    jr $ra
    
    if1:
    move $v0 $a0           # Returns a0
    jr $ra

    .data