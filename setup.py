#!/usr/bin/python3

import os
import requests
import sys

structure_template = 'src/{}/kotlin/solutions/day{}'

solver_template = """
package solutions.{package}

import solutions.Solver

class {clazz}: Solver {{
    override fun solve(input: List<String>, partTwo: Boolean): String {{
        TODO("not implemented")
    }}
}}
"""

test_template = """
package solutions.{package}

import solutions.AbstractDayTest

class {clazz}Test: AbstractDayTest({clazz}()) {{
    override fun getPart1Data(): List<TestData> = listOf(
           TestData(listOf(""), "")
    )

    override fun getPart2Data(): List<TestData> = listOf(
           TestData(listOf(""), "")
    )

}}
"""


def generate_files(day, kind, template):
    day_as_string = str(day).zfill(2)
    clazz = 'Day{}'.format(day)
    package = 'day{}'.format(day_as_string)
    folder = structure_template.format(kind, day_as_string)
    os.makedirs(folder)
    suffix = ''
    if kind == 'test':
        suffix = 'Test'

    path = "{}/{}.{}".format(folder, clazz+suffix, 'kt')
    content = template.format(package=package, clazz=clazz)

    with open(path, 'w') as f:
        f.write(content)

    return folder


def fetch_input(day, token, folder):
    url = 'https://adventofcode.com/2017/day/{}/input'.format(day)
    res = requests.get(url, cookies={'session': token})
    input_file = '{}/input'.format(folder)
    with open(input_file, 'wb') as f:
        for chunk in res.iter_content(chunk_size=128):
            f.write(chunk)


if __name__ == '__main__':
    if len(sys.argv) != 3:
        print("Usage: setup.py token day")
        exit(1)

    token = sys.argv[1]
    day = sys.argv[2]

    day_int = -1
    try:
        day_int = int(day)
    except ValueError:
        print("Could not parse day as number: [{}]".format(day))
        exit(1)

    folder = generate_files(day_int, 'main', solver_template)
    generate_files(day_int, 'test', test_template)
    fetch_input(day_int, token, folder)
