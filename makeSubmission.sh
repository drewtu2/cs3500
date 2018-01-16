#!/bin/bash

function usage {
    echo Usage:
    echo "./makeSubmission <folder>"
    echo "The directory structure should be the following:"
    echo "  - Must contain a src and test folder containing the java files"
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
    
    target_dir=${1%/}
    new_dir=$target_dir$submission_suffix
    tar_file=$target_dir$tar_suffix
   
    # Copy the targeted directory into a scratch directory we can manipulate 
    cp -r $target_dir $new_dir
    cd $new_dir
    
    files=(test src)
    if [ -f report.txt ]; then
            echo "Including report.txt"
            files+=(report.txt)
    fi
    if [ -f graph.png ]; then
            echo "Including graph.png"
            files+=(graph.png)
    fi

    tar -czvf $tar_file ${files[@]}
    
    mv $tar_file ..
    cd ..
    rm -rf $new_dir
else
    echo Couldn\'t find the directory specified....
fi

