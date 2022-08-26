def getbranch() {
  branch = "${env.BRANCH_NAME}"
  branch = (branch == null || branch == "null") ? "master": branch
  return branch
}

def getImageURI(Registry, RegistryRepo, Tag){
  def imageURI = Registry + "$RegistryRepo:$Tag"

  echo $imageURI
  return imageURI
}