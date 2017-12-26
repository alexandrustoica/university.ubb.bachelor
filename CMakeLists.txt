cmake_minimum_required(VERSION 3.9)
project(greyscale)

set(CMAKE_CXX_STANDARD 14)

add_executable(greyscale main.cu include/png.cpp)

include_directories(${CMAKE_CURRENT_SOURCE_DIR}/include)

install(FILES ./include/png.h DESTINATION include)