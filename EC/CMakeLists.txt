cmake_minimum_required(VERSION 3.6)
project(ecproj)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11")

set(SOURCE_FILES ectest.cpp ecListFuncs.cpp ecListFuncs.h)
add_executable(ecproj ${SOURCE_FILES})