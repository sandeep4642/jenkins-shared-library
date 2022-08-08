def getbranch() {
  branch = "${env.BRANCH_NAME}"
  branch = (branch == null || branch == "null") ? "master": branch
  return branch
}