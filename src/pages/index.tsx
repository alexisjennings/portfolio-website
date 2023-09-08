import { Button, Box, Paper, Stack, Typography } from "@mui/material";

export default function Page() {
   return (
      <Box
         sx={{
            pt: 10,
            pb: 6,
         }}
      >
         <Typography
            sx={{ fontWeight: "medium", pl: 3 }}
            component="h1"
            variant="h3"
            align="left"
            gutterBottom
         >
            welcome. : )
         </Typography>
         <Typography
            sx={{ fontWeight: "medium", pl: 3, pt: 1 }}
            component="h2"
            variant="h4"
            align="left"
            gutterBottom
         >
            shortcuts.
         </Typography>
         <Box className="flex justify-center">
            <Paper sx={{ padding: 3 }}>
               <Stack direction="row" spacing={1}>
                  <Button variant="contained" href="/about">
                     About
                  </Button>
                  <Button variant="contained" href="/projects">
                     Projects
                  </Button>
                  <Button variant="contained" href="/contact">
                     Contact
                  </Button>
               </Stack>
            </Paper>
         </Box>
      </Box>
   );
}
