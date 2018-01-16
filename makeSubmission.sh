#!/bin/bash

function usage {
    echo Usage:
    echo "./makeSubmission <folder> <directory structure flag>"
    echo "The directory structure can be one of the following:"
    echo "    0: Structured directory i.e. src and include folders..."
    echo "    1: Flat directory (no internal organization)..."
}

if [ "$#" -lt 1 ];
then
    echo Need at least one argument....
    usage
    echo Exiting ....
    exit 1
elif [ -d "$1" ];
then
    tar_suffix=".tar.gz"
    submission_suffix="_submission"
    
    new_dir=$1$submission_suffix
    tar_file=$1$tar_suffix
   
    # Copy the targeted directory into a scratch directory we can manipulate 
    cp -r $1 $new_dir
    cd $new_dir
    
    cd ..

    files=($new_dir/test $new_dir/src)
    if [ -f $new_dir/report.txt ]; then
            echo "Including report.txt"
            files+=($new_dir/report.txt)
    fi
    if [ -f $new_dir/graph.png ]; then
            echo "Including graph.png"
            files+=($new_dir/graph.png)
    fi

    tar -czvf $tar_file ${files[@]}
    
    rm -rf $new_dir
else
    echo Couldn\'t find the directory specified....
fi

