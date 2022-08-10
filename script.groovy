def buildApp()
{
   echo 'build application'
}
def testApp()
{
   echo 'test application'
}
def deployApp()
{
    echo 'deploy application'
    echo "deploying ${SERVER_CRED}"
    sh " ${SERVER_CRED}"
}
return this
