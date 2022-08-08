def getbranch() {
  branch = bat(returnStdout: true, script: basename ${env.BRANCH_NAME} | cut -d'-' -f1-2").trim()
  branch = (branch == null || branch == "null") ? "master": branch
  return branch
}