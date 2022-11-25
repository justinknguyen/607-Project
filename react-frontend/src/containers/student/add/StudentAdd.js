import * as React from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Container from '@mui/material/Container'
import Paper from '@mui/material/Paper'
import Button from '@mui/material/Button'
import { useState } from 'react';

export default function StudentAdd() {
  const paperStyle = {padding:'50px 20px', width:600, margin:'20px auto'}
  const [cid,setCid] = useState('')

  const handleClick=(e)=>{
    e.preventDefault()
    const course={cid}
    console.log(course)
    // TODO: send data to database
  }

  return (
    <Container>
      <Paper elevation={3} style={paperStyle}>
        <h1>Add Course</h1>
        <p>Enroll into a course</p>

    <Box
      component="form"
      sx={{
        '& > :not(style)': { m: 1, width: 500, maxWidth: '100%' },
      }}
      noValidate
      autoComplete="off"
    >
      <TextField required id="outlined-required" label="Course ID (e.g., ENSF608)" variant="outlined" fullWidth
      value = {cid}
      onChange={(e)=>setCid(e.target.value)}
      />
      
      <Button variant="contained" onClick={handleClick}>
        Submit
      </Button>
    </Box>
    </Paper>
    </Container>
  );
}

