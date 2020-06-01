Android Cryptography

Create a program that allows multiple ways to encrypt/decrypt messages

Why are we doing this?

This is a mathematically important topic in Computer Science; plus, we are going to have a treasure hunt where you get to use your app on a tablet!

Overview:  Create a program that has three possible ways to encrypt/decrypt a message.

Interface:  Create two text views. One will be text view where a decrypted message will be placed, while the other will be where the encrypted message is located. Between the two text views, include an “encrypt” button and a “decrypt” button.

The interface will also have three radio buttons that will allow the user to select from the Scytale (rhymes with “Italy”) Cipher, Caesar Cipher, or Vigenere (vee-jun-air) Cipher. Include appropriate controls for the column height for Scytale, offset for Caesar, and keyword for the Vigenere Cipher. (These could be in the main layout, or you can prompt the user.) (5 points)

Implementation: When you hit the “encrypt” button, use the currently selected encryption to encode the message from decrypted text area, and overwrite the encrypted area. The “decrypt” button should do the opposite. You must convert all letters to uppercase, and remove any characters that are not letters. (5 points for conversion)

For the Scytale Cipher, you should take the characters from the input, put them into a two-dimensional array. The height of each column (that is, the number of rows), will also be read in. Fill across with your original message, and then read down for encryption. You may use an “@” sign for filling in the end for empty slots in the two-dimensional array, if that makes it easier. For decryption, fill down in the two-dimensional array, and read across. (10 points)

For the Caesar Cipher, you should shift the letters in a message based on a number. (If the number is four, ‘A’ becomes ‘E’, ‘B’ becomes ‘F’, ‘Z’ becomes ‘D’, and so on.) For decryption, shift the message in the opposite direction. (10 points) (Note: you might want a way to show all 26 possible shifts. This is not required, but will help with the treasure hunt.)

For the Vigenere Cipher, use a function that is similar to the Caesar Cipher where each letter of the keyword is a shift, and encrypt the message by repeated application of the keyword. Make sure you clean up the keyword in the same way as your input, meaning uppercase and letters only! (Note: ‘A’ does not shift the alphabet, ‘B’ shifts the alphabet by one, etc.) Reverse the function to decrypt it. (10 points)

Your program MUST WORK ON A NEXUS 7 TABLET. (5 points)

Comments! (5 points)

You complete the encryption treasure hunt using your own program. (5 points)

Total: 55 points

 

Here are some examples to help:

“Plaintext Message” encrypted with Scytale, 4 rows is: “PNTSLTMAAEEGIXSE”

Decrypting “PNTSLTMAAEEGIXSE” with Scytale, 4 rows is: “PLAINTEXTMESSAGE”

“Plaintext Message” encrypted with Caesar, shift of 3 is: “SODLQWHAWPHVVDJH”

Decrypting “SODLQWHAWPHVVDJH” with Caesar, shift of 3 is: “PLAINTEXTMESSAGE”

“Plaintext Message” encrypted with Vigenere, key of “java” is: “YLVIWTZXCMZSBABE”

Decrypting “YLVIWTZXCMZSBABE” with Vigenere, key of “java” is: “PLAINTEXTMESSAGE”
