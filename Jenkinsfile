pipeline{
    agent any
  tools{
     jdk 'java8'
     maven 'maven'
  }
    environment{
       SERVER_CRED=credentials('server')
    }
  parameters{
    string(name: 'Branch',defaultValue: 'main',description: 'please type branch')
    choice(name: 'Version',choices: ['1.2.0','1.2.1','1.2.2'],description: 'select version')
    booleanParam(name: 'executeTests',defaultValue: true,description: 'test')
  }
  stages{
    stage("init"){
      steps{
        script{
           gv=load "script.groovy"
        }
      }
    }
    stage("build"){
     steps{
        script{
          gv.buildApp()
        }
      }
    }
    stage("test"){
         when{
        expression{params.executeTests}
      }
     steps{
        script{
          gv.testApp()
        }
      }
    }
    stage("deploy"){
      when{
        expression{params.Version=='1.2.0'}
      }
     steps{
         echo "deploying ${SERVER_CRED}"
         sh " ${SERVER_CRED}"
        script{
          gv.deployApp()
        }
      }
    }
  }
}
