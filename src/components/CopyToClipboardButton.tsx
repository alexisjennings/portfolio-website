import { Box, Button, Snackbar } from "@mui/material";
import { useState } from "react";

export default function CopyToClipboardButton({
   textToCopy,
}: {
   textToCopy: string;
}) {
   const [open, setOpen] = useState(false);
   const handleClick = () => {
      setOpen(true);
      navigator.clipboard.writeText(textToCopy);
   };

   return (
      <>
         <Button onClick={handleClick}>Copy</Button>
         <Snackbar
            open={open}
            onClose={() => setOpen(false)}
            autoHideDuration={2000}
            message="Copied to clipboard"
         ></Snackbar>
      </>
   );
}
