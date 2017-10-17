cmake_minimum_required(VERSION 3.6)
project(proj)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")


add_executable(grades grades.cpp Table.h Table.cpp listFuncs.cpp listFuncs.h)
add_executable(concord concord.cpp Table.h Table.cpp listFuncs.cpp listFuncs.h)
add_executable(pa5list pa5list.cpp Table.h Table.cpp listFuncs.cpp listFuncs.h)