import React, { Component } from 'react';

import TopNav from './component/TopNav';
import LeftNav from './component/LeftNav';
import DashboardPage from './component/DashboardPage';
import AlterHistoryPage from './component/AlterHistoryPage';
import AlterValidationPage from './component/AlterValidationPage';
import LoginModal from './component/LoginModal';
import AddAlterTableModal from './component/AddAlterTableModal';
import {
    BrowserRouter as Router,
    Route,
    NavLink
} from 'react-router-dom';
import Button from 'react-bootstrap/Button';
import Alert from 'react-bootstrap/Alert';


class App extends Component {

    state = {
        apiBaseUrl: "http://localhost:8080/api",
        isLogin: false,
        isLoginModalShow: false,
        isAddAlterMoDalShow: false,
        isAlertShow: false,
        isDryRunClicked: false,
        userEmail: "",
        userPassword: "",
        modalAlterTransactionNo: -1,
        modalAlterShardName: "default",
        modalAlterNodeName: "default",
        modalAlterDatabaseName: "",
        modalAlterTableName: "",
        modalAlterSyntax: "",
        modalAlterDate: "",
        modalAlterHour: "",
        ansibleData:[],
        ghostNodeList:["test-bae-client1.testdb","test-bae-client2.testdb","test-bae-client3.testdb"],
        tableDatas: [
            /*
            {
                alterTransactionNo: 1,
                alterExcuteDate: 'N/A',
                alterExcuteHour: 'N/A',
                alterShardName: 'order-euaaa',
                alterDatabaseName: 'taxiy_company',
                alterTableName: 'order',
                alterSyntax: 'N/A',
                alterTableSize: 'N/A',
                alterTableRows: 'N/A',
                alterETA: 'N/A',
                alterRegistrationDate: 'N/A',
                alterStatus: 'N/A',
                alterRequistor: 'N/A'
            }
            <td>{item.orderId+1}</td>
            <td>{item.createTimestamp}</td>
            <td>{item.updateTimestamp}</td>
            <td>{item.clusterName}</td>
            <td>{item.tableSchema}</td>
            <td>{item.tableName}</td>
            <td>{item.createTimestamp}</td>
            <td>{progressStatus}</td>
            <td>{item.registerEmail}</td>
            */
        ],
        graghOption: {
            title:{
                text: "Alter - per day"
            },
            axisX: {
                valueFormatString: "MMM-DD",
                interval:1,
                intervalType: "day"
              },
            data: [
                {
                    type: "line",
            
                    dataPoints: [
                        { x: new Date(2019, 0, 1), y: 0 },
                        { x: new Date(2019, 0, 2), y: 0 },
                        { x: new Date(2019, 0, 3), y: 0 },
                        { x: new Date(2019, 0, 4), y: 0 },
                        { x: new Date(2019, 0, 5), y: 0 },
                        { x: new Date(2019, 0, 6), y: 0 },
                        { x: new Date(2019, 0, 7), y: 0 }
                    ]
            }]
        },
        ansibleData: [],
        dryrunData: []
    };



    actionLoginModal = () => {
    
        if (this.state.isLogin){
            // If user click log out botton
            this.setState(prevState => ({
                isLogin: !prevState.isLogin
            }))

            window.localStorage.clear()
            
        }else {
            // First rendering
            // If user click log in botton
            this.setState(prevState => ({
                isLoginModalShow: !prevState.isLoginModalShow
            }))
            
        }
        
        
    };




    actionAddAlterModal = alterTransactionNo  => {
        console.log('adfsdf', alterTransactionNo)
        console.log(this.state.tableDatas)
        // if log in status then show add alter table modal
        if(this.state.isLogin){
            
            this.setState(prevState => ({
                isAddAlterMoDalShow: !prevState.isAddAlterMoDalShow,
            }))

             // this will define click ADD or click Edit
            if (alterTransactionNo === 0){
                this.setState({
                    modalAlterTransactionNo: alterTransactionNo
                })    
            }else{

                // alterTransactionNo is not array number, it is data!!!
                // Get index number of array 
                let tableDatasIndex = this.state.tableDatas.findIndex((element)=>{return element.orderId === alterTransactionNo })

                console.log(tableDatasIndex)

                this.setState({
                    modalAlterTransactionNo: this.state.tableDatas[tableDatasIndex].orderId,
                    modalAlterShardName: this.state.tableDatas[tableDatasIndex].clusterName,
                    modalAlterDatabaseName: this.state.tableDatas[tableDatasIndex].tableSchema,
                    modalAlterTableName: this.state.tableDatas[tableDatasIndex].tableName,
                    // modalAlterSyntax: this.state.tableDatas[tableDatasIndex].alterSyntax,
                    modalAlterDate: this.state.tableDatas[tableDatasIndex].alterExcuteDate,
                    modalAlterHour: this.state.tableDatas[tableDatasIndex].alterExcuteHour,
                })
                
            }
            
    
        }else{
            // if use is not logged in show alert
            // alert("You need to login first")
            this.setState(prevState => ({
                isAlertShow: !prevState.isAlertShow
            }))
        }
 
    };




    actionAlert = () => {
        this.setState(prevState => ({
            isAlertShow: !prevState.isAlertShow
        }))
    };




    actionValueChange = event => {

        const { name, value } = event.target;

        if(name === 'logInSubmitButton') {
            // click login submit
            // TO-DO Later call api

            /*
            setTimeout(()=> {
                console.log("login callbackup after 3 sec")
                this.setState({
                    isLogin: true,
                    isLoginModalShow: false
                })
            }, 3000);
            */

            
            var xhr = new XMLHttpRequest()

            var returnObj

            xhr.open('POST', this.state.apiBaseUrl+'/login/login')
            xhr.setRequestHeader('Content-Type', 'application/json')
   
            xhr.send(JSON.stringify({ 
               "razielUser" : this.state.userEmail,
               "razielPassword" : this.state.userPassword
            }))
   
            xhr.addEventListener('load', () => {
                console.log("addEventListener message : " + xhr.responseText)
                returnObj = JSON.parse(xhr.responseText)

                if(returnObj.status === 1){

                    this.setState({
                        isLogin: true,
                        isLoginModalShow: false
                    })

                    var now = new Date().getTime()
                    localStorage.setItem('loginTime', now)
                    window.localStorage.setItem('loginUser', this.state.userEmail)

                }else{
                    this.setState({
                        isLogin: false,
                        isLoginModalShow: true
                    })

                }

            })

            


            
        }else if (name === 'addAlterTableSubmitButton'){
            // click add alter table  submit
            // TO-DO Later call api
            
            setTimeout(()=> {
                console.log("add alter table  callbackup after 3 sec")
                this.setState({
                    isAddAlterMoDalShow: false
                })
            }, 3000);
            
            

        }else if (name === 'DryRunSubmitButton'){
            // click add alter table  submit
            // TO-DO Later call api

            /*
            if(this.state.isLogin){

            }else{
                // if use is not logged in show alert
                // alert("You need to login first")
                this.setState(prevState => ({
                isAlertShow: !prevState.isAlertShow
                }))
            }
            */
            
            if(this.state.isLogin){

                var ansible = new XMLHttpRequest()
 
                // Ansible
                
                ansible.open('POST', this.state.apiBaseUrl+'/ansible/find')
                ansible.setRequestHeader('Content-Type', 'application/json')
        
                ansible.send(JSON.stringify({ 
                    "tableName" : this.state.modalAlterTableName,
                    "tableSchema" : this.state.modalAlterDatabaseName,
                    "clusterName" : this.state.modalAlterShardName,
                    "alterStatement" : this.state.modalAlterSyntax.split(','),
                    "registerEmail" : this.state.userEmail,
                }))
        
                ansible.addEventListener('load', () => {
                    console.log("addEventListener message : " + ansible.responseText)
                    var returnObj = JSON.parse(ansible.responseText)
                    
                    if(ansible.status === 200){
                        this.setState({
                            ansibleData: returnObj
                        })
                    }
                    
                })
                

                
                // Dry run
                
                var dryrun = new XMLHttpRequest()
    
                dryrun.open('POST', this.state.apiBaseUrl+'/ghost/dryrun')
                dryrun.setRequestHeader('Content-Type', 'application/json')
        
                dryrun.send(JSON.stringify({ 
                    "tableName" : this.state.modalAlterTableName,
                    "tableSchema" : this.state.modalAlterDatabaseName,
                    "clusterName" : this.state.modalAlterShardName,
                    "alterStatement" : this.state.modalAlterSyntax.split(','),
                    "registerEmail" : this.state.userEmail,
                }))
        
                dryrun.addEventListener('load', () => {
                    console.log("addEventListener message : " + dryrun.responseText)
                    var returnObj = JSON.parse(dryrun.responseText)
                    
                    if(dryrun.status === 200){
                        this.setState({
                            dryrunData: returnObj.outputStrList,
                            isDryRunClicked: true
                        })
                    }
                    
                })

            }else{
                // if use is not logged in show alert
                // alert("You need to login first")
                this.setState(prevState => ({
                isAlertShow: !prevState.isAlertShow
                }))
            }



            

        }else if (name === 'modalAlterShardName'){

            this.setState({
                [ name ]: value
            })

            // read-only update

            var updateReadOnly = new XMLHttpRequest()

            updateReadOnly.open('POST', this.state.apiBaseUrl+'/mysqlhost/updateReadOnly')
            updateReadOnly.setRequestHeader('Content-Type', 'application/json')
            updateReadOnly.send(JSON.stringify({ 
                "clusterName" : value
            }))

            updateReadOnly.addEventListener('load', () => {
                console.log("addEventListener message : " + updateReadOnly.responseText)
                var returnObj = JSON.parse(updateReadOnly.responseText)
                
                if(updateReadOnly.status === 200){
                    console.log("read-only update successful.")
                }
                
            })

        }else if (name === 'ExecuteSubmitButton'){

            // Execute Alter
            
            var executeAlter = new XMLHttpRequest()

            executeAlter.open('POST', this.state.apiBaseUrl+'/ghost/execute')
            executeAlter.setRequestHeader('Content-Type', 'application/json')
    
            executeAlter.send(JSON.stringify({ 
                "tableName" : this.state.modalAlterTableName,
                "tableSchema" : this.state.modalAlterDatabaseName,
                "clusterName" : this.state.modalAlterShardName,
                "alterStatement" : this.state.modalAlterSyntax.split(','),
                "registerEmail" : this.state.userEmail
            }))
            
            executeAlter.addEventListener('load', () => {
                

                /*
                console.log("addEventListener message : " + executeAlter.responseText)
                var returnObj = JSON.parse(executeAlter.responseText)
                
                if(executeAlter.status === 200){
                    console.log("executeAlter status : " + executeAlter.status)
                }
                */
                
            })

            alert("Alter executed! Refresh the page.")

            setTimeout(()=> {
                window.location.replace("/");
            }, 1000);







        }else if (name === 'modalAlterNodeName'){

            // ghost host update
            /*
            * if this.state.modalAlterShardName is  'default', this choose gh-ost node will
            * update the mysql_host table with 'default'
            * which make wrong data
            */
            if( this.state.modalAlterShardName != 'default' && value != "default"){


                var updateGhostHost = new XMLHttpRequest()

                updateGhostHost.open('POST', this.state.apiBaseUrl+'/mysqlhost/updateGhostHost')
                updateGhostHost.setRequestHeader('Content-Type', 'application/json')

                updateGhostHost.send(JSON.stringify({ 
                    "clusterName" : this.state.modalAlterShardName,
                    "hostName" : value,
                    "hostType" : 3
                }))  

                updateGhostHost.addEventListener('load', () => {
                    console.log("addEventListener message : " + updateGhostHost.responseText)
                    var returnObj = JSON.parse(updateGhostHost.responseText)

                    if(updateGhostHost.status === 200){
                        console.log("Ghost host update successful.")
                    }

                })

            }


        }else {
            // email, password, shardName ....
            this.setState({
                [ name ]: value
            })
        }
        
        
    };





    actionNav = event => {
        const { name, value } = event.target;
        console.log(name)
    };






    renderLoginModal = () => {
        return this.state.isLogin?
            // While login status 
            <LoginModal 
                isShow={false} 
                actionLoginModal={this.actionLoginModal}
                userEmail={this.state.userEmail}
                userPassword={this.state.userPassword}
                actionValueChange={this.actionValueChange}
            />
        :
            // First rendering
            // whenever email, password types
            // Not login status
            <LoginModal 
                isShow={this.state.isLoginModalShow} 
                actionLoginModal={this.actionLoginModal}
                userEmail={this.state.userEmail}
                userPassword={this.state.userPassword}
                actionValueChange={this.actionValueChange}
            />
    };
        



    renderAddAlterModal = () => {

        return <AddAlterTableModal 
                    isShow={this.state.isAddAlterMoDalShow} 
                    actionAddAlterModal={this.actionAddAlterModal} 
                    actionValueChange={this.actionValueChange}
                    modalAlterTransactionNo={this.state.modalAlterTransactionNo}
                    modalAlterShardName={this.state.modalAlterShardName}
                    modalAlterDatabaseName={this.state.modalAlterDatabaseName}
                    modalAlterTableName={this.state.modalAlterTableName}
                    modalAlterSyntax={this.state.modalAlterSyntax}
                    modalAlterDate={this.state.modalAlterDate}
                    modalAlterHour={this.state.modalAlterHour} 
               />
    };
    




    renderAlert = () => {
        return (
            <>
                <Alert show={this.state.isAlertShow} variant="success">
                <Alert.Heading>How's it going?!</Alert.Heading>
                <p>
                    Please log in before you make any changes.<br></br>
                    Thank you.
                </p>
                <hr />
                <div className="d-flex justify-content-end">
                    <Button onClick={this.actionAlert} variant="outline-success">
                        Close
                    </Button>
                </div>
                </Alert>
            </>
        )   
    };




    renderAlterExecute = actionValueChange => {

        if(this.state.isDryRunClicked){
            return <div className="form-row">
                        <div className="form-group col-md-6">
                        <Button name="ExecuteSubmitButton" type='button' variant="primary" className="btn btn-primary" 
                            onClick={actionValueChange}
                        >
                            Execute
                        </Button>
                        </div>
                    </div>
        }else{
            return <div className="form-row"></div>
        }

    };





    componentDidMount(){
        // TO-DO Later call api for get current schedule for alter
        /*
        this.setState({
            tableDatas: [
                {
                    alterTransactionNo: 1,
                    alterExcuteDate: '15-08-2019',
                    alterExcuteHour: '03',
                    alterShardName: 'ORDER-EU',
                    alterDatabaseName: 'taxify_compony',
                    alterTableName: 'order',
                    alterTableSize: '141G',
                    alterTableRows: '125,987,938 rows',
                    alterETA: '10H',
                    alterRegistrationDate: '20-07-2019',
                    alterStatus: 'Registerd',
                    alterRequistor: 'Sangsun'
                },
                {
                    alterTransactionNo: 2,
                    alterExcuteDate: '07-08-2019',
                    alterExcuteHour: '04',
                    alterShardName: 'ORDER-EU',
                    alterDatabaseName: 'taxify_compony',
                    alterTableName: 'order',
                    alterTableSize: '141G',
                    alterTableRows: '125,987,938 rows',
                    alterETA: '10H',
                    alterRegistrationDate: '21-07-2019',
                    alterStatus: 'Registerd',
                    alterRequistor: 'Sangsun'
                }
            ]
        })
        */

        

        var userName = window.localStorage.getItem('loginUser')

        if(userName !== null){

            console.log('stored userName : '+userName)

            var now = new Date().getTime();
            var loginTime = window.localStorage.getItem('loginTime')
            
            if( now - loginTime > 24 * 60 * 60 * 1000){
                window.localStorage.clear();
            }else{
                this.setState(prevState => ({
                    isLogin: true,
                    userEmail: userName
                }))
            }

        }

        var xhr = new XMLHttpRequest()

        xhr.open('POST', this.state.apiBaseUrl+'/ghost/findAll')
        xhr.setRequestHeader('Content-Type', 'application/json')

        xhr.send(JSON.stringify({ }))

        xhr.addEventListener('load', () => {
            console.log("addEventListener message : " + xhr.responseText)
            this.setState({
                tableDatas: JSON.parse(xhr.responseText)
            })
        })

    }





    render(){

        return(
            <div>
                {this.renderAlert()}
                <Router>
                    <TopNav>
                        <button name="loginButton" className="btn btn-big btn-primary" onClick={this.actionLoginModal} >{this.state.isLogin? 'Log out':'Log in' }</button>
                        {this.renderLoginModal()}
                    </TopNav>
                    <div className="container-fluid" >
                        <div className="row">
                            <LeftNav>
                                <li className="nav-item">
                                    <NavLink name='dashboard' className="nav-link active" to='/dashboard' onClick={this.actionNav}>
                                        <span data-feather="home"></span>
                                        Dash Board 
                                        <span className="sr-only">(current)</span>
                                    </NavLink>
                                </li>
                                <li className="nav-item">
                                    <NavLink name='alterHistory' className="nav-link" to='/history' onClick={this.actionNav}>
                                        <span data-feather="file"></span>
                                        Alter History
                                    </NavLink>
                                </li>
                                <li className="nav-item">
                                    <NavLink name='alterValidation' className="nav-link" to='/validation' onClick={this.actionNav}>
                                        <span data-feather="file"></span>
                                        Alter Validation
                                    </NavLink>
                                </li>
                            </LeftNav>
                            <main  role="main" className="col-md-9 ml-sm-auto col-lg-10 px-4">
                                <Route path='/'          render={(props) => <DashboardPage {...props} 
                                    actionAddAlterModal={this.actionAddAlterModal}
                                    tableDatas={this.state.tableDatas}
                                    graghOption={this.state.graghOption}
                                />}  exact/>
                                <Route path='/dashboard' render={(props) => <DashboardPage {...props} 
                                    actionAddAlterModal={this.actionAddAlterModal}
                                    tableDatas={this.state.tableDatas}
                                    graghOption={this.state.graghOption}
                                />}  exact/>
                                <Route path='/history' render={(props) => <AlterHistoryPage {...props}
                                    tableDatas={this.state.tableDatas}
                                />}   exact/>
                                <Route path='/validation' render={(props) => <AlterValidationPage {...props}
                                    renderAlterExecute={this.renderAlterExecute}
                                    ansibleData={this.state.ansibleData}
                                    ghostNodeList={this.state.ghostNodeList}
                                    dryrunData={this.state.dryrunData} 
                                    actionValueChange={this.actionValueChange}
                                />}   exact/>
                            </main>
                            {this.renderAddAlterModal()}
                        </div>
                    </div>
                </Router>

            </div>
        );
    };

}

export default App;