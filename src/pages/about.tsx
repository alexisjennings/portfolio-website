import { Box, Chip, Container, Stack, Typography, Paper } from "@mui/material";

export default function Page() {
   return (
      <Box
         sx={{
            pt: 10,
            pb: 6,
         }}
      >
         <Container maxWidth="md">
            <Typography
               sx={{ fontWeight: "medium", pl: 3 }}
               component="h1"
               variant="h4"
               align="left"
               gutterBottom
            >
               about.
            </Typography>
            <Paper sx={{ mx: 3 }}>
               <Typography sx={{ px: 3, pt: 3 }} variant="body1" align="left">
                  hi! I&apos;m Alexis, a 22 year-old developer from Dallas,
                  Texas. yeehaw!
               </Typography>
               <Typography sx={{ px: 3, pt: 3 }} variant="body1" align="left">
                  likes:
               </Typography>
               <Box className="flex justify-left" sx={{ mb: 1, px: 3, pt: 1 }}>
                  <Stack direction="row" spacing={1}>
                     <Chip
                        label="programming"
                        color="primary"
                        size="small"
                        className="font-medium"
                     />
                     <Chip
                        label="cats"
                        color="primary"
                        size="small"
                        className="font-medium"
                     />
                     <Chip
                        label="video games"
                        color="primary"
                        size="small"
                        className="font-medium"
                     />
                     <Chip
                        label="music"
                        color="primary"
                        size="small"
                        className="font-medium"
                     />
                  </Stack>
               </Box>
               <Typography sx={{ px: 3, pt: 3 }} variant="body1" align="left">
                  favorite tools:
               </Typography>
               <Box
                  className="flex justify-left"
                  sx={{ mb: 1, px: 3, pb: 3, pt: 1 }}
               >
                  <Stack direction="row" spacing={1}>
                     <Chip
                        label="Java"
                        color="primary"
                        size="small"
                        className="font-medium"
                     />
                     <Chip
                        label="Python"
                        color="primary"
                        size="small"
                        className="font-medium"
                     />
                     <Chip
                        label="TypeScript"
                        color="primary"
                        size="small"
                        className="font-medium"
                     />
                     <Chip
                        label="React"
                        color="primary"
                        size="small"
                        className="font-medium"
                     />
                  </Stack>
               </Box>
            </Paper>
            <Typography
               sx={{ fontWeight: "medium", pl: 3, pt: 1 }}
               component="h1"
               variant="h4"
               align="left"
               gutterBottom
            >
               about this site.
            </Typography>
            <Paper sx={{ mx: 3 }}>
               <Typography sx={{ padding: 3 }} variant="body1" align="left">
                  this website was built using the Next.js web framework with
                  React components and written using TypeScript.
               </Typography>
               <Typography sx={{ px: 3, pb: 3 }} variant="body1" align="left">
                  the GitHub repository for this website can be found on my
                  projects page.
               </Typography>
            </Paper>
         </Container>
      </Box>
   );
}
