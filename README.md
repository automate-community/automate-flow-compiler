# Getting started

Automate flow compiler is an open source project that that transforms a simple program written with code into a flow.
Our goal is to make the language simple and intuitive so that new users have a more linear learning.

# Building the project

### Programming language

For the programming language syntax, we based in [LUA](https://www.lua.org/) language.

### Lexer and parser

We don't need a custom lexer and parser, so we decided to use [ANTLR Parser Generator](https://www.antlr.org/) to make our lives easier.
Also, ANTLR has built-in grammar parsers, so implementing [LUA](https://github.com/antlr/grammars-v4/tree/master/lua) would be easier.

### Automate statements

For automate statements and libraries we are going to use [AuTool](https://github.com/KweezyCode/AuTool).
